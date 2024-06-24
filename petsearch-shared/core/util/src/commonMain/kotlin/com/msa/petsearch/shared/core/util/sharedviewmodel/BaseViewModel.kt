package com.msa.petsearch.shared.core.util.sharedviewmodel

import co.touchlab.skie.configuration.annotations.FlowInterop
import com.msa.petsearch.shared.core.util.resource.MessageDeque
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.ActionDispatcher
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Navigation
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.stateIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<A : Action, N : Navigation, E : Event>(
    val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel(), ActionDispatcher<A>, RouteNavigator {
    val vScope
        get() = viewModelScope.coroutineScope

    private val _events by lazy { MutableSharedFlow<E>() }
    @FlowInterop.Enabled
    val events by lazy { _events.asSharedFlow() }

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    @FlowInterop.Enabled
    override val navigationEvent: SharedFlow<NavigationEvent>
        get() = _navigationEvent.asSharedFlow()

    /** <T: N> is a walk-around for the following issue
     *
     * [See issue](https://youtrack.jetbrains.com/issue/KT-44972/JVM-IR-Exception-during-psi2ir-when-suppressing-conflicting-overloads-error-to-enforce-use-of-parameter-names)
     */
    protected fun <T: N> emit(navigation: T) {
        vScope.launch {
            delay(navigation.delay)
            _navigationEvent.emit(navigation.event)
        }
    }

    protected fun emit(event: E) {
        vScope.launch { _events.emit(event) }
    }

    protected fun emit(message: ResourceMessage) {
        vScope.launch { MessageDeque.enqueue(message) }
    }

    protected fun <T> Flow<T>.stateInWhileSubscribed(
        started: SharingStarted = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue: T
    ) = stateIn(viewModelScope = viewModelScope, started = started, initialValue = initialValue)
}
