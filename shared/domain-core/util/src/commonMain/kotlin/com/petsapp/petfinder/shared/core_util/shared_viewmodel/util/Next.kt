package com.petsapp.petfinder.shared.core_util.shared_viewmodel.util

import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux

sealed class Next<out S : NanoRedux.State, out SE : NanoRedux.SideEffect, out E : NanoRedux.Event, out N : NanoRedux.Navigation, out ER : NanoRedux.Error> {

    abstract val state: S
    open val sideEffects: Set<SE> = emptySet()
    open val events: Set<E> = emptySet()
    open val navigation: Set<N> = emptySet()
    open val errors: Set<ER> = emptySet()


    data class State<out S : NanoRedux.State>(override val state: S)
        : Next<S, Nothing, Nothing, Nothing, Nothing>()

    data class StateWithSideEffects<out S : NanoRedux.State, out SE : NanoRedux.SideEffect>(
        override val state: S,
        override val sideEffects: Set<SE>
    ) : Next<S, SE, Nothing, Nothing, Nothing>()

    data class StateWithSideEffectsAndEvents<out S : NanoRedux.State, out SE : NanoRedux.SideEffect, out E : NanoRedux.Event>(
        override val state: S,
        override val sideEffects: Set<SE>,
        override val events: Set<E>
    ) : Next<S, SE, E, Nothing, Nothing>()

    data class StateWithEvents<out S : NanoRedux.State, out E : NanoRedux.Event>(
        override val state: S,
        override val events: Set<E>
    ) : Next<S, Nothing, E, Nothing, Nothing>()

    data class StateWithNavigation<out S : NanoRedux.State, out N : NanoRedux.Navigation>(
        override val state: S,
        override val navigation: Set<N>
    ) : Next<S, Nothing, Nothing, N, Nothing>()

    data class StateWithNavigationAndEvents<out S : NanoRedux.State, out E : NanoRedux.Event, out N : NanoRedux.Navigation>(
        override val state: S,
        override val navigation: Set<N>,
        override val events: Set<E>
    ) : Next<S, Nothing, E, N, Nothing>()

    data class StateWithError<out S : NanoRedux.State, out ER : NanoRedux.Error>(
        override val state: S,
        override val errors: Set<ER>
    ) : Next<S, Nothing, Nothing, Nothing, ER>()

}