package com.msa.petsearch.shared.domain.homeuicontract.contract

import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.Updater
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.GlobalEvent
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.Next
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.ReduxGeneric
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeAction
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeNavigation
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeSideEffect
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeState

typealias NextResult = Next<HomeState, HomeSideEffect, GlobalEvent, HomeNavigation, ReduxGeneric.Error>

class HomeUpdater :
    Updater<HomeAction, HomeState, HomeSideEffect, GlobalEvent, HomeNavigation, ReduxGeneric.Error> {

    override fun onNewAction(action: HomeAction, currentState: HomeState): NextResult {
        return when (action) {
            is HomeAction.GetPetTypes -> loadPetTypes(currentState)
            is HomeAction.UpdatePetTypesInState -> updatePetTypesResponse(action, currentState)

            is HomeAction.UpdatePetResponseInState -> updateSearchPetResponse(action, currentState)

            is HomeAction.OnPetTypeTabSelected -> onSelectedPetTypeChanged(action, currentState)

            is HomeAction.NavigateToPetDetail -> navigateToPetDetail(action, currentState)

            is HomeAction.LoadPetListNextPage -> loadPetListNextPage(currentState)
            is HomeAction.OnLoadPetListNextPageActionComplete -> onLoadPetListNextPageActionComplete(currentState)

            is HomeAction.Error ->
                onError(ReduxGeneric.Error(message = action.message), currentState)
        }
    }

    private fun loadPetTypes(state: HomeState): NextResult {
        return Next
            .StateWithSideEffects(
                state = state,
                sideEffects = setOf(HomeSideEffect.LoadPetTypesFromNetwork)
            )
    }

    private fun updatePetTypesResponse(
        action: HomeAction.UpdatePetTypesInState,
        state: HomeState
    ): NextResult {
        val sideEffects = action.petTypesResponse?.types?.firstOrNull()?.name
            ?.let {
                setOf(
                    HomeSideEffect.LoadPetsFromNetwork(
                        type = it,
                        page = 1,
                        params = state.searchParams
                    )
                )
            }
            ?: setOf()

        return Next
            .StateWithSideEffects(
                state = state.copy(petTypesResponse = action.petTypesResponse),
                sideEffects = sideEffects
            )
    }

    private fun updateSearchPetResponse(
        action: HomeAction.UpdatePetResponseInState,
        state: HomeState
    ): NextResult {
        return Next.State(state = state.copy(petPagingData = action.petPagingData,))
    }

    private fun onSelectedPetTypeChanged(
        action: HomeAction.OnPetTypeTabSelected,
        state: HomeState
    ) : NextResult {
        return Next
            .StateWithSideEffects(
                state = state,
                sideEffects = setOf(
                    HomeSideEffect.LoadPetsFromNetwork(
                        type = action.tabName,
                        page = 1,
                        params = state.searchParams
                    )
                )
            )
    }

    private fun loadPetListNextPage(state: HomeState): NextResult {
        return Next
            .StateWithSideEffects(
                state = state,
                sideEffects = setOf(HomeSideEffect.LoadPetListNextPageFromNetwork)
            )
    }

    private fun onLoadPetListNextPageActionComplete(state: HomeState): NextResult {
        // A method that does nothing
        return Next.State(state = state)
    }

    private fun navigateToPetDetail(
        action: HomeAction.NavigateToPetDetail,
        state: HomeState
    ) : NextResult {
        return Next
            .StateWithNavigation(
                state = state,
                navigation = setOf(HomeNavigation.NavHomeToPetDetail(action.petInfo))
            )
    }
}
