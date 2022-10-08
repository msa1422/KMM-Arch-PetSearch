package com.msa.petsearch.shared.domain.homeuicontract.contract

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreutil.commonflow.asCommonFlow
import com.msa.petsearch.shared.coreutil.extension.loadNextPage
import com.msa.petsearch.shared.coreutil.resource.MessageType
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.Processor
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeAction
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeSideEffect
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetsUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.UseCaseWrapper
import kotlinx.coroutines.flow.distinctUntilChanged

internal class HomeProcessor(
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
            is HomeSideEffect.LoadPetTypesFromNetwork -> {
                useCases.getPetTypes(arg = Unit, onError = ::onError)
            }
            is HomeSideEffect.LoadPetsFromNetwork -> getPetListPagedFlow(effect)
            is HomeSideEffect.LoadPetListNextPageFromNetwork -> {
                petListPager.loadNextPage()
                return HomeAction.OnLoadPetListNextPageActionComplete
            }
        }
    }

    private suspend fun getPetListPagedFlow(
        effect: HomeSideEffect.LoadPetsFromNetwork
    ): HomeAction {
        if (!this::petListPager.isInitialized ||
            !this::currentPetType.isInitialized ||
            currentPetType != effect.type
        ) {
            petListPager = Pager(
                clientScope = effect.coroutineScope,
                config = pagingConfig,
                initialKey = 1
            ) { currentKey, _ ->
                useCases.getPets(LoadPetsUseCase.Params(effect.type, currentKey, effect.params))
            }
        }

        val flow = petListPager
            .pagingData
            .distinctUntilChanged()
            .cachedIn(effect.coroutineScope)
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
}
