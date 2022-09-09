package com.petsapp.petfinder.shared.core_util.shared_viewmodel.store

import com.benasher44.uuid.uuid4
import com.petsapp.petfinder.shared.core_util.resource.ResourceMessage
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.coroutines.DispatcherProvider
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

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
            get() = DispatcherProvider.backgroundDispatcher()
        val coroutineScope: CoroutineScope
            get() = CoroutineScope(dispatcher)
    }

}
