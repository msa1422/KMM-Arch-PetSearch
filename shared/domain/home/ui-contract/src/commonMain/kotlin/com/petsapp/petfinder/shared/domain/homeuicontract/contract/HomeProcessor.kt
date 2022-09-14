package com.petsapp.petfinder.shared.domain.homeuicontract.contract

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo
import com.petsapp.petfinder.shared.coreutil.asCommonFlow
import com.petsapp.petfinder.shared.coreutil.extension.loadNextPage
import com.petsapp.petfinder.shared.coreutil.resource.MessageType
import com.petsapp.petfinder.shared.coreutil.resource.ResourceMessage
import com.petsapp.petfinder.shared.coreutil.resource.Status.ERROR
import com.petsapp.petfinder.shared.coreutil.resource.Status.SUCCESS
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.Processor
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.HomeAction
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.HomeSideEffect
import com.petsapp.petfinder.shared.domain.homeuicontract.interactor.LoadPetsUseCase
import com.petsapp.petfinder.shared.domain.homeuicontract.interactor.UseCaseWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeProcessor(
    private val useCases: UseCaseWrapper
) : Processor<HomeSideEffect, HomeAction> {

    private lateinit var petListPager: Pager<Int, PetInfo>
    private lateinit var currentPetType: String

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 8,
        enablePlaceholders = false,
        initialLoadSize = 30
    )

    override suspend fun dispatchSideEffect(effect: HomeSideEffect): HomeAction {
        return when (effect) {
            is HomeSideEffect.LoadPetTypesFromNetwork -> loadPetTypes(effect)
            is HomeSideEffect.LoadPetsFromNetwork -> getPetListPagedFlow(effect)
            is HomeSideEffect.LoadPetListNextPageFromNetwork -> loadNextPage()
        }
    }

    private suspend fun loadPetTypes(
        effect: HomeSideEffect.LoadPetTypesFromNetwork
    ) : HomeAction {
        return suspendCancellableCoroutine { continuation ->
            useCases.getPetTypes.execute(
                coroutineScope = effect.coroutineScope,
                dispatcher = effect.dispatcher
            ) {
                onNext { resource ->
                    when (resource.status) {
                        SUCCESS ->
                            continuation.resume(HomeAction.UpdatePetTypesInState(resource.data))
                        ERROR -> continuation.resume(onError(resource.throwable))
                        else -> {} // Ignore
                    }
                }
            }
        }
    }

    private suspend fun getPetListPagedFlow(
        effect: HomeSideEffect.LoadPetsFromNetwork
    ) : HomeAction {
        if (!this::petListPager.isInitialized ||
            !this::currentPetType.isInitialized ||
            currentPetType != effect.type
        ) {
            petListPager = Pager(
                clientScope = effect.coroutineScope,
                config = pagingConfig,
                initialKey = 1
            ) { currentKey, _ ->
                triggerLoadPetListUseCase(effect, currentKey)
            }
        }

        val flow = petListPager
            .pagingData
            .distinctUntilChanged()
            .cachedIn(effect.coroutineScope)
            .asCommonFlow()

        return HomeAction.UpdatePetResponseInState(flow)
    }

    private suspend fun loadNextPage(): HomeAction {
        return suspendCancellableCoroutine {
            petListPager.loadNextPage()
            it.resume(HomeAction.OnLoadPetListNextPageActionComplete)
        }
    }

    private suspend fun triggerLoadPetListUseCase(
        effect: HomeSideEffect.LoadPetsFromNetwork,
        currentKey: Int
    ) : PagingResult<Int, PetInfo> {
        return suspendCancellableCoroutine { continuation ->
            useCases.getPets.execute(
                args = LoadPetsUseCase.Params(effect.type, currentKey, effect.params),
                coroutineScope = effect.coroutineScope,
                dispatcher = effect.dispatcher
            ) {
                onNext { resource ->
                    when {
                        resource.status == SUCCESS && !resource.data?.animals.isNullOrEmpty() -> {
                            val result = PagingResult(
                                items = resource.data?.animals!!,
                                currentKey = currentKey,
                                prevKey = { null },
                                nextKey = {
                                    val totalPageCount =
                                        resource.data?.pagination?.totalCount ?: 1
                                    currentKey.takeIf { it < totalPageCount }?.plus(1)
                                }
                            )

                            continuation.resume(result)
                        }

                        resource.status == ERROR -> {
                            val result = PagingResult(
                                items = emptyList<PetInfo>(),
                                currentKey = currentKey,
                                prevKey = { null },
                                nextKey = {
                                    val totalPageCount =
                                        resource.data?.pagination?.totalCount ?: 1
                                    currentKey.takeIf { it < totalPageCount }?.plus(1)
                                }
                            )

                            continuation.resume(result)
                        }

                        else -> {} // Ignore
                    }
                }
            }
        }
    }

    private fun onError(throwable: Throwable?): HomeAction {
        return HomeAction.Error(
            message = ResourceMessage(
                text = throwable?.message,
                messageType = MessageType.SnackBar()
            )
        )
    }
}
