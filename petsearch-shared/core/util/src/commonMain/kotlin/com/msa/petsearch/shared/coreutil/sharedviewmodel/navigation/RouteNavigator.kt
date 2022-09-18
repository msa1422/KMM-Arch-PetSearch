package com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation

import kotlinx.coroutines.flow.StateFlow

/**
 * A simplified rewrite of the RouteNavigator from the source below
 * @see <a href="https://github.com/Frank1234/ViewModelNavigationCompose">
 *
 * Navigator to use when initiating navigation from a ViewModel.
 */
interface RouteNavigator {
    val navigationState: StateFlow<NavigationState>
    fun onNavStart(state: NavigationState)
    fun onNavComplete(state: NavigationState)
}
