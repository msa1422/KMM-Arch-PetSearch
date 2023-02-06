package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

import com.msa.petsearch.shared.core.util.commonflow.asCommonSharedFlow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class RouteNavigatorImpl : RouteNavigator {
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    override val navigationEvent = _navigationEvent.asCommonSharedFlow()

    override suspend fun onNavEvent(event: NavigationEvent) = _navigationEvent.emit(event)
}
