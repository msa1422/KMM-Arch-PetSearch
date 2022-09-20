package com.msa.petsearch.shared.coreutil.sharedviewmodel.store

import com.benasher44.uuid.uuid4
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

sealed interface NanoRedux {

    interface State : NanoRedux

    interface RenderState : NanoRedux

    interface Event : NanoRedux {
        val id: String
            get() = uuid4().toString()
    }

    interface Action : NanoRedux

    interface Error : NanoRedux {
        val message: ResourceMessage?
            get() = null
    }

    interface Navigation : NanoRedux {
        val state: NavigationState
        val delay: Long
            get() = 0
    }

    interface SideEffect : NanoRedux {
        val dispatcher: CoroutineDispatcher
            get() = Dispatchers.Default
        val coroutineScope: CoroutineScope
            get() = CoroutineScope(SupervisorJob() + dispatcher)
    }
}
