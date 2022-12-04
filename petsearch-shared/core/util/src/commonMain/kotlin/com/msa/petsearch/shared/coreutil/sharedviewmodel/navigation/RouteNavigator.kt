package com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation

import com.msa.petsearch.shared.coreutil.commonflow.CommonStateFlow

/**
 * A simplified rewrite of the RouteNavigator from the source below
 * @see <a href="https://github.com/Frank1234/ViewModelNavigationCompose">
 *
 * Navigator to use when initiating navigation from a ViewModel.
 */
interface RouteNavigator {
    val navigationState: CommonStateFlow<NavigationState>
    fun onNavStart(state: NavigationState)
    fun onNavComplete(state: NavigationState)
}
