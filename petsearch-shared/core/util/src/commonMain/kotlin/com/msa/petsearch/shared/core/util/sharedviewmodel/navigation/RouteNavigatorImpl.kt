package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class RouteNavigatorImpl : RouteNavigator {
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override suspend fun onNavEvent(event: NavigationEvent) = _navigationEvent.emit(event)
}
