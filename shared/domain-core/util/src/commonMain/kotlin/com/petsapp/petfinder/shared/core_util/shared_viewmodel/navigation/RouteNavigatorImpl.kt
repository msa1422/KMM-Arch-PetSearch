package com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class RouteNavigatorImpl: RouteNavigator {

    /**
     * Note that I'm using a single state here, not a list of states. As a result, if you quickly
     * update the state multiple times, the view will only receive and handle the latest state,
     * which is fine for my use case.
     */
    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavComplete(state: NavigationState)  {
        // clear navigation state, if state is the current state:
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun onNavStart(state: NavigationState) {
        navigationState.value = state
    }

}