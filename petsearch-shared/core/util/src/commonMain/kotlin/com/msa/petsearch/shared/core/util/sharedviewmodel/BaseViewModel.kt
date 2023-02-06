package com.msa.petsearch.shared.core.util.sharedviewmodel

import com.msa.petsearch.shared.core.util.commonflow.asCommonSharedFlow
import com.msa.petsearch.shared.core.util.commonflow.asCommonStateFlow
import com.msa.petsearch.shared.core.util.resource.MessageDeque
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.SuperViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.ActionDispatcher
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.ArgsMapper
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.Navigation
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.RenderState
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.SideEffect
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux.State
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.Processor
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.StateMapper
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.Updater
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Heavily modified version of the Arch Library written by fededri
 * [GitHub](https://github.com/fededri/Arch)
 */
abstract class BaseViewModel<A : Action, S : State, SE : SideEffect, RS : RenderState, N : Navigation, E : Event>
constructor(
    private val updater: Updater<A, S, SE, E, N>? = null,
    private val initialState: S,
    private val processor: Processor<SE, A>? = null,
    private val stateMapper: StateMapper<S, RS>? = null,
    private val argsMapper: ArgsMapper<S>? = null,
    private val coroutineExceptionHandler: CoroutineExceptionHandler? = null,
    private val routeNavigator: RouteNavigator,
    initialEffects: Set<SE> = emptySet()
) : SuperViewModel(), ActionDispatcher<A>, RouteNavigator by routeNavigator {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow().asCommonStateFlow()

    private val _renderState by lazy {
        _state.map { stateMapper?.mapToRenderState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = null
            )
    }
    val renderState by lazy { _renderState.asCommonStateFlow() }

    private val _events by lazy { MutableSharedFlow<E>() }
    val events by lazy { _events.asSharedFlow().asCommonSharedFlow() }

    init { dispatchSideEffects(initialEffects) }

    fun updateArgsInState(args: HashMap<String, String>) {
        argsMapper?.let { mapper ->
            _state.update { mapper.mapArgsToState(currentState = it, args = args) }
        }
    }

    override fun action(action: A) {
        updater?.onNewAction(action, state.value)?.let { next ->
            next.events.forEach {
                viewModelScope.launch { _events.emit(it) }
            }

            if (next.sideEffects.isNotEmpty()) {
                dispatchSideEffects(next.sideEffects)
            }

            next.navigation.firstOrNull()?.let {
                viewModelScope.launch { routeNavigator.onNavEvent(it.state) }
            }

            next.errors.forEach {
                viewModelScope.launch {
                    MessageDeque.enqueue(it.copy(dequeueCallback = MessageDeque::dequeue))
                }
            }

            _state.update { next.state }
        }
    }

    private fun dispatchSideEffects(sideEffects: Set<SE>) {
        processor?.let { processor ->
            sideEffects.forEach { effect ->
                val coroutineContext = coroutineExceptionHandler ?: EmptyCoroutineContext
                viewModelScope.launch(coroutineContext) {
                    withContext(effect.dispatcher) {
                        val effectAction = processor.dispatchSideEffect(effect)
                        withContext(viewModelScope.coroutineContext) {
                            action(effectAction)
                        }
                    }
                }
            }
        }
    }

    public override fun onCleared() {
        _state.update { initialState }
        processor?.close()
        super.onCleared()
    }
}
