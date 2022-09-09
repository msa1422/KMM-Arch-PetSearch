package com.petsapp.petfinder.shared.core_util.shared_viewmodel.store

import com.petsapp.petfinder.shared.core_util.shared_viewmodel.util.Next
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux.*


interface Updater<A : Action, S : State, SE : SideEffect, E : Event, N : Navigation, ER: Error> {

    fun onNewAction(action: A, currentState: S): Next<S, SE, E, N, ER>

    fun onError(error: ER, currentState: S): Next<S, SE, E, N, ER> {
        return Next.StateWithError(state = currentState, errors = setOf(error))
    }

}