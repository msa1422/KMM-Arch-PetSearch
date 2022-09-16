package com.msa.petsearch.shared.coreutil.sharedviewmodel.util

import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux

sealed class Next<out S : NanoRedux.State, out SE : NanoRedux.SideEffect, out E : NanoRedux.Event, out N : NanoRedux.Navigation, out ER : NanoRedux.Error> {

    abstract val state: S
    open val sideEffects: Set<SE> = emptySet()
    open val events: Set<E> = emptySet()
    open val navigation: Set<N> = emptySet()
    open val errors: Set<ER> = emptySet()

    data class State<out S : NanoRedux.State, out E : NanoRedux.Event>(
        override val state: S,
        override val events: Set<E> = setOf()
    ) : Next<S, Nothing, E, Nothing, Nothing>()

    data class StateWithSideEffects<out S : NanoRedux.State, out SE : NanoRedux.SideEffect, out E : NanoRedux.Event>(
        override val state: S,
        override val sideEffects: Set<SE>,
        override val events: Set<E> = setOf()
    ) : Next<S, SE, E, Nothing, Nothing>()

    data class StateWithNavigation<out S : NanoRedux.State, out E : NanoRedux.Event, out N : NanoRedux.Navigation>(
        override val state: S,
        override val navigation: Set<N>,
        override val events: Set<E> = setOf()
    ) : Next<S, Nothing, E, N, Nothing>()

    data class StateWithError<out S : NanoRedux.State, out ER : NanoRedux.Error>(
        override val state: S,
        override val errors: Set<ER>
    ) : Next<S, Nothing, Nothing, Nothing, ER>()
}
