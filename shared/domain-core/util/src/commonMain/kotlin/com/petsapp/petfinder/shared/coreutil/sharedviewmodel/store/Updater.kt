package com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store

import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux.*
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.Next

interface Updater<A : Action, S : State, SE : SideEffect, E : Event, N : Navigation, ER : Error> {

    fun onNewAction(action: A, currentState: S): Next<S, SE, E, N, ER>

    fun onError(error: ER, currentState: S): Next<S, SE, E, N, ER> {
        return Next.StateWithError(state = currentState, errors = setOf(error))
    }
}
