package com.msa.petsearch.shared.core.util.sharedviewmodel

import com.msa.petsearch.shared.core.util.resource.MessageDeque
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.SuperViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Navigation
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel2<A : Action, N : Navigation, E : Event, S: State>
constructor(
    private val emptyArgs: S? = null,
    private val argsMapper: ArgsMapper2<S>? = null,
    private val routeNavigator: RouteNavigator,
) : SuperViewModel(), ActionDispatcher2<A>, RouteNavigator by routeNavigator  {

    protected val navArgs by lazy {
        requireNotNull(emptyArgs) {
            "In order to use navArgs, constructor param emptyArgs must not be null."
        }
        MutableStateFlow(emptyArgs)
    }

    private val _events by lazy { MutableSharedFlow<E>() }
    val events by lazy { _events.asSharedFlow() }

    /** <T: N> is a walk-around for the following issue
     *
     * [See issue](https://youtrack.jetbrains.com/issue/KT-44972/JVM-IR-Exception-during-psi2ir-when-suppressing-conflicting-overloads-error-to-enforce-use-of-parameter-names)
     */
    protected fun <T: N> emit(navigation: T) {
        viewModelScope.launch {
            delay(navigation.delay)
            routeNavigator.onNavEvent(navigation.event)
        }
    }

    protected fun emit(event: E) {
        viewModelScope.launch { _events.emit(event) }
    }

    protected fun emit(message: ResourceMessage) {
        viewModelScope.launch { MessageDeque.enqueue(message) }
    }

    fun updateArgs(map: HashMap<String, String>) {
        require(map.isNotEmpty()) { "ArgsMap should not be empty." }
        requireNotNull(argsMapper) { "ArgsMapper should not be null." }

        navArgs.update { argsMapper.stateFrom(map) }
    }
}

interface ActionDispatcher2<A : Action> {
    fun dispatch(action: A)
}

interface ArgsMapper2<S : State> {
    fun stateFrom(args: HashMap<String, String>): S
}
