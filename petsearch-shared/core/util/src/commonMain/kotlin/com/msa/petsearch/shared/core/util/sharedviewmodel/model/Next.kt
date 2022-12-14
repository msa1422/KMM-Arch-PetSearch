package com.msa.petsearch.shared.core.util.sharedviewmodel.model

import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Navigation
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.SideEffect
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.State

sealed class Next<out S : State, out SE : SideEffect, out E : Event, out N : Navigation> {

    abstract val state: S
    open val sideEffects: Set<SE> = emptySet()
    open val events: Set<E> = emptySet()
    open val navigation: Set<N> = emptySet()
    open val errors: Set<ResourceMessage> = emptySet()

    data class State<out S : NanoRedux.State, out E : Event>(
        override val state: S,
        override val events: Set<E> = setOf()
    ) : Next<S, Nothing, E, Nothing>()

    data class StateWithSideEffects<out S : NanoRedux.State, out SE : SideEffect, out E : Event>(
        override val state: S,
        override val sideEffects: Set<SE>,
        override val events: Set<E> = setOf()
    ) : Next<S, SE, E, Nothing>()

    data class StateWithNavigation<out S : NanoRedux.State, out E : Event, out N : Navigation>(
        override val state: S,
        override val navigation: Set<N>,
        override val events: Set<E> = setOf()
    ) : Next<S, Nothing, E, N>()

    data class StateWithError<out S : NanoRedux.State>(
        override val state: S,
        override val errors: Set<ResourceMessage>
    ) : Next<S, Nothing, Nothing, Nothing>()
}
