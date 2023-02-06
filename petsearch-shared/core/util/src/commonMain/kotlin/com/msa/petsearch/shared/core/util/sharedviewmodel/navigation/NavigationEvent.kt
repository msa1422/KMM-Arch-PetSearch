package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

import com.benasher44.uuid.uuid4

sealed class NavigationEvent {
    data class NavigateToRoute(
        val id: String = uuid4().toString(),
        val route: String,
        val delay: Long = 0,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()

    data class PopToRoute(
        val id: String = uuid4().toString(),
        val staticRoute: String,
        val delay: Long = 0,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()

    data class NavigateAndPopUpToRoute(
        val id: String = uuid4().toString(),
        val route: String,
        val popUpTo: String,
        val delay: Long = 0,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()

    data class NavigateUp(
        val id: String = uuid4().toString(),
        val inclusive: Boolean = false,
        val delay: Long = 0,
        val args: HashMap<String, String>? = null
    ) : NavigationEvent()
}
