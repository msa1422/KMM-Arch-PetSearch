package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

import com.msa.petsearch.shared.core.util.commonflow.asCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RouteNavigatorImpl : RouteNavigator {

    /**
     * Note that I'm using a single state here, not a list of states. As a result, if you quickly
     * update the state multiple times, the view will only receive and handle the latest state,
     * which is fine for my use case.
     */
    private val _navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override val navigationState = _navigationState.asStateFlow().asCommonStateFlow()

    override fun onNavComplete(state: NavigationState) {
        _navigationState.update { NavigationState.Idle }
    }

    override fun onNavStart(state: NavigationState) {
        _navigationState.update { state }
    }
}
