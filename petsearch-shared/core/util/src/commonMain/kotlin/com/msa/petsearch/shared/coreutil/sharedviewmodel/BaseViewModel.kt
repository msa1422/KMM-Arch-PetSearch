package com.msa.petsearch.shared.coreutil.sharedviewmodel

import com.msa.petsearch.shared.coreutil.commonflow.asCommonFlow
import com.msa.petsearch.shared.coreutil.commonflow.asCommonStateFlow
import com.msa.petsearch.shared.coreutil.resource.MessageDeque
import com.msa.petsearch.shared.coreutil.sharedviewmodel.model.SuperViewModel
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.*
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 *
 * Heavily modified version of the Arch Library written by fededri
 * https://github.com/fededri/Arch
 *
 */
abstract class BaseViewModel<A : Action, S : State, SE : SideEffect, RS : RenderState, N : Navigation, E : Event, ER : Error>
constructor(
    private val updater: Updater<A, S, SE, E, N, ER>? = null,
    private val initialState: S,
    private val processor: Processor<SE, A>? = null,
    private val stateMapper: StateMapper<S, RS>? = null,
    private val argsMapper: ArgsMapper<S>? = null,
    private val coroutineExceptionHandler: CoroutineExceptionHandler? = null,
    private val routeNavigator: RouteNavigator,
    private val messageDeque: MessageDeque
) : SuperViewModel(), ActionDispatcher<A>, RouteNavigator by routeNavigator {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow().asCommonStateFlow()

    private val _renderState by lazy {
        _state.map { stateMapper?.mapToRenderState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
    }
    val renderState by lazy { _renderState.asCommonStateFlow() }

    private val _events = MutableSharedFlow<E>()
    val events = _events.asSharedFlow().asCommonFlow()

    val messageFlow = messageDeque.readOnlyStateFlow().asCommonStateFlow()

    fun updateArgsInState(args: HashMap<String, String>) {
        argsMapper?.let { mapper ->
            _state.value = mapper.mapArgsToState(currentState = _state.value, args = args)
        }
    }

    override fun action(action: A) {
        updater?.onNewAction(action, state.value)?.let { next ->

            viewModelScope.launch {
                next.events.forEach {
                    _events.emit(it)
                }
            }

            if (next.sideEffects.isNotEmpty()) {
                dispatchSideEffects(next.sideEffects)
            }

            next.navigation.firstOrNull()?.let {
                routeNavigator.onNavStart(it.state)
            }

            next.errors.forEach { error ->
                error.message?.let {
                    val messageWithCallback = it.copy(
                        text = it.text,
                        messageType = it.messageType,
                        dequeueCallback = { messageDeque.dequeue() }
                    )

                    messageDeque.enqueue(messageWithCallback)
                }
            }

            _state.value = next.state

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
                        withContext(Dispatchers.Main) {
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
