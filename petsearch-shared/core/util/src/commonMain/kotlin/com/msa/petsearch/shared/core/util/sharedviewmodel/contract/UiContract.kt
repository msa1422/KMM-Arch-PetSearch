package com.msa.petsearch.shared.core.util.sharedviewmodel.contract

import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent

sealed interface UiContract {
    interface Action : UiContract

    interface NavArgs: UiContract

    interface Event : UiContract {
        val id: String
    }

    interface Navigation : UiContract {
        val event: NavigationEvent
        val delay: Long
            get() = 0
    }
}
