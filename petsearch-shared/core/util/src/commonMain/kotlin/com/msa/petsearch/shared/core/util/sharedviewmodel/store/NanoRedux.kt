package com.msa.petsearch.shared.core.util.sharedviewmodel.store

import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

sealed interface NanoRedux {

    interface State : NanoRedux

    interface RenderState : NanoRedux

    interface Event : NanoRedux {
        val id: String
    }

    interface Action : NanoRedux

    interface Navigation : NanoRedux {
        val state: NavigationEvent
        val delay: Long
            get() = 0
    }

    interface SideEffect : NanoRedux {
        val dispatcher: CoroutineDispatcher
            get() = Dispatchers.Default
    }
}
