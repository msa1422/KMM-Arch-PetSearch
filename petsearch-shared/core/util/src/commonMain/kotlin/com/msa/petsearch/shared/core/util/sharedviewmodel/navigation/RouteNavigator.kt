package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

import kotlinx.coroutines.flow.SharedFlow

/**
 * A simplified rewrite of the RouteNavigator from the source below
 * @see <a href="https://github.com/Frank1234/ViewModelNavigationCompose">
 *
 * Navigator to use when initiating navigation from a ViewModel.
 */
interface RouteNavigator {
    val navigationEvent: SharedFlow<NavigationEvent>
    suspend fun onNavEvent(event: NavigationEvent)
}
