package com.petsapp.petfinder.shared.domain.home_ui_contract.contract

import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_util.resource.MessageType
import com.petsapp.petfinder.shared.core_util.resource.ResourceMessage
import com.petsapp.petfinder.shared.core_util.resource.Status
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.Processor
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store.HomeAction
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store.HomeSideEffect
import com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor.LoadPetsUseCase
import com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor.UseCaseWrapper
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
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
            is HomeSideEffect.LoadPetsFromNetwork -> loadPagedPetListFromNetwork(effect)
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
                        Status.SUCCESS ->
                            continuation.resume(HomeAction.UpdatePetTypesInState(resource.data))
                        Status.ERROR -> continuation.resume(onError(resource.throwable))
                        else -> {} // Ignore
                    }
                }
            }

        }
    }

    private suspend fun loadPagedPetListFromNetwork(
        effect: HomeSideEffect.LoadPetsFromNetwork
    ) : HomeAction {

        if (!this::petListPager.isInitialized
            || !this::currentPetType.isInitialized
            || currentPetType != effect.type
        ) {
            petListPager = Pager(
                clientScope = effect.coroutineScope,
                config = pagingConfig,
                initialKey = 1,
                getItems = { currentKey, _ ->

                    suspendCancellableCoroutine { continuation ->
                        useCases.getPets.execute(
                            args = LoadPetsUseCase.Params(effect.type, currentKey, effect.params),
                            coroutineScope = effect.coroutineScope,
                            dispatcher = effect.dispatcher
                        ) {
                            onNext { resource ->
                                when {
                                    resource.status == Status.SUCCESS
                                            && !resource.data?.animals.isNullOrEmpty() -> {

                                        val result = PagingResult(
                                            items = resource.data?.animals!!,
                                            currentKey = currentKey,
                                            prevKey = { null },
                                            nextKey = {
                                                val totalPageCount = resource.data?.pagination?.totalCount ?: 1
                                                currentKey.takeIf { it < totalPageCount }?.plus(1)
                                            }
                                        )

                                        continuation.resume(result)

                                    }
                                    resource.status == Status.ERROR -> {

                                        val result = PagingResult(
                                            items = emptyList<PetInfo>(),
                                            currentKey = currentKey,
                                            prevKey = { null },
                                            nextKey = {
                                                val totalPageCount = resource.data?.pagination?.totalCount ?: 1
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
            )
        }

        return HomeAction.UpdatePetResponseInState(
            petListPager
                .pagingData
                .distinctUntilChanged()
                .cachedIn(effect.coroutineScope)
                .first()
        )

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