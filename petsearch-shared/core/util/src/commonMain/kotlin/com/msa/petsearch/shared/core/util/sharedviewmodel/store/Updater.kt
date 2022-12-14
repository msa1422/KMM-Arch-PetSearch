package com.msa.petsearch.shared.core.util.sharedviewmodel.store

import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.Next
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Navigation
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.SideEffect
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.State

interface Updater<A : Action, S : State, SE : SideEffect, E : Event, N : Navigation> {
    fun onNewAction(action: A, currentState: S): Next<S, SE, E, N>

    fun onError(error: ResourceMessage, currentState: S): Next<S, SE, E, N> {
        return Next.StateWithError(state = currentState, errors = setOf(error))
    }
}
