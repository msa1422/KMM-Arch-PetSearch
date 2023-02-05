package com.msa.petsearch.shared.ui.home.contract

import com.msa.petsearch.shared.core.util.sharedviewmodel.model.GlobalEvent
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.Next
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.Updater
import com.msa.petsearch.shared.ui.home.contract.store.Error
import com.msa.petsearch.shared.ui.home.contract.store.ForwardInitialDataToState
import com.msa.petsearch.shared.ui.home.contract.store.ForwardPetResponseToState
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction
import com.msa.petsearch.shared.ui.home.contract.store.HomeNavigation
import com.msa.petsearch.shared.ui.home.contract.store.HomeSideEffect
import com.msa.petsearch.shared.ui.home.contract.store.HomeState
import com.msa.petsearch.shared.ui.home.contract.store.IdleAction
import com.msa.petsearch.shared.ui.home.contract.store.LoadPetListNextPage
import com.msa.petsearch.shared.ui.home.contract.store.NavigateToPetDetail
import com.msa.petsearch.shared.ui.home.contract.store.OnPetTypeTabChanged

typealias NextResult = Next<HomeState, HomeSideEffect, GlobalEvent, HomeNavigation>

internal class HomeUpdater :
    Updater<HomeAction, HomeState, HomeSideEffect, GlobalEvent, HomeNavigation> {

    override fun onNewAction(action: HomeAction, currentState: HomeState) = when (action) {
        is ForwardInitialDataToState -> copyInitialData(action, currentState)

        is OnPetTypeTabChanged -> onPetTypeTabChanged(action, currentState)
        is ForwardPetResponseToState -> copyPetResponse(action, currentState)

        is LoadPetListNextPage -> loadPetListNextPage(action, currentState)
        is IdleAction -> Next.State(state = currentState)

        is NavigateToPetDetail -> navigateToPetDetail(action, currentState)

        is Error -> onError(action.message, currentState)
    }

    private fun copyInitialData(action: ForwardInitialDataToState, state: HomeState): NextResult =
        Next.State(state.copy(petTypes = action.petTypes, petPagingData = action.petPagingData))

    private fun copyPetResponse(action: ForwardPetResponseToState, state: HomeState): NextResult =
        Next.State(state = state.copy(petPagingData = action.petPagingData))

    private fun onPetTypeTabChanged(action: OnPetTypeTabChanged, state: HomeState): NextResult =
        Next.StateWithSideEffects(state = state, sideEffects = setOf(action))

    private fun loadPetListNextPage(action: LoadPetListNextPage, state: HomeState): NextResult =
        Next.StateWithSideEffects(state = state, sideEffects = setOf(action))

    private fun navigateToPetDetail(action: NavigateToPetDetail, state: HomeState): NextResult =
        Next.StateWithNavigation(state = state, navigation = setOf(action))
}
