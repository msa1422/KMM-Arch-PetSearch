package com.petsapp.petfinder.shared.domain.homeuicontract.contract

import com.petsapp.petfinder.shared.coreutil.asCommonFlow
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.Updater
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.GlobalEvent
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.Next
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.ReduxGeneric
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.HomeAction
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.HomeNavigation
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.HomeSideEffect
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.HomeState
import kotlinx.coroutines.flow.flow

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
        return Next.State(
            state = state.copy(
                petPagingData = flow { emit(action.petPagingData) }.asCommonFlow(),
            )
        )
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
