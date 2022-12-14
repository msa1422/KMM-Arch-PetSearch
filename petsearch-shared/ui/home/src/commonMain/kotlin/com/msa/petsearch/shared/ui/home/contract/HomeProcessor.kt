package com.msa.petsearch.shared.ui.home.contract

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.commonflow.asCommonFlow
import com.msa.petsearch.shared.core.util.extension.loadNextPage
import com.msa.petsearch.shared.core.util.resource.MessageType
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.resource.Status
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.Processor
import com.msa.petsearch.shared.domain.home.HomeUseCaseWrapper
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction
import com.msa.petsearch.shared.ui.home.contract.store.HomeSideEffect
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.distinctUntilChanged

internal class HomeProcessor(
    private val useCases: HomeUseCaseWrapper
) : Processor<HomeSideEffect, HomeAction> {

    private lateinit var petListPager: Pager<Int, PetInfo>
    private lateinit var currentPetType: String
    private lateinit var coroutineScope: CoroutineScope

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 8,
        enablePlaceholders = false,
        initialLoadSize = 30
    )

    override suspend fun dispatchSideEffect(effect: HomeSideEffect): HomeAction {
        return when (effect) {
            is HomeSideEffect.LoadPetTypesFromNetwork -> getPetTypes()
            is HomeSideEffect.LoadPetsFromNetwork -> getPetListPagedFlow(effect)
            is HomeSideEffect.LoadPetListNextPageFromNetwork -> {
                petListPager.loadNextPage()
                return HomeAction.OnLoadPetListNextPageActionComplete
            }
        }
    }

    private suspend fun getPetTypes(): HomeAction {
        val resource = useCases.getPetTypes()
        return when (resource.status) {
            Status.SUCCESS -> HomeAction.UpdatePetTypesInState(petTypesResponse = resource.data)
            else -> onError(resource.throwable)
        }
    }

    private suspend fun getPetListPagedFlow(
        effect: HomeSideEffect.LoadPetsFromNetwork
    ): HomeAction {
        if (!this::petListPager.isInitialized ||
            !this::currentPetType.isInitialized ||
            currentPetType != effect.type
        ) {
            if (!this::coroutineScope.isInitialized) {
                val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    onError(throwable)
                }

                coroutineScope =
                    CoroutineScope(SupervisorJob() + Dispatchers.Main + exceptionHandler)
            }

            petListPager = Pager(
                clientScope = coroutineScope,
                config = pagingConfig,
                initialKey = 1
            ) { currentKey, _ ->
                useCases.getPets(LoadPetsUseCase.Params(effect.type, currentKey, effect.params))
            }
        }

        val flow = petListPager
            .pagingData
            .distinctUntilChanged()
            .cachedIn(coroutineScope)
            .asCommonFlow()

        return HomeAction.UpdatePetResponseInState(flow)
    }

    private fun onError(throwable: Throwable?): HomeAction {
        return HomeAction.Error(
            message = ResourceMessage(
                text = throwable?.message,
                messageType = MessageType.SnackBar()
            )
        )
    }

    override fun close() {
        if (this::coroutineScope.isInitialized) {
            coroutineScope.cancel()
        }
    }
}
