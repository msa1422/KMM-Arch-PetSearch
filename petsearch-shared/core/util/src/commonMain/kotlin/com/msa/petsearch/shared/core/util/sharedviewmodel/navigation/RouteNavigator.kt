package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.SharedFlow

/**
 * A simplified rewrite of the RouteNavigator from the source below
 * @see <a href="https://github.com/Frank1234/ViewModelNavigationCompose">
 *
 * Navigator to use when initiating navigation from a ViewModel.
 */
interface RouteNavigator {
    @NativeCoroutines
    val navigationEvent: SharedFlow<NavigationEvent>
}
