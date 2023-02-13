package com.msa.petsearch.shared.core.util.sharedviewmodel

import com.msa.petsearch.shared.core.util.resource.MessageDeque
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.ActionDispatcher
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.ArgsMapper
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.NavArgs
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Navigation
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.SuperViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<A : Action, N : Navigation, E : Event, NA: NavArgs>
constructor(
    private val emptyArgs: NA? = null,
    private val argsMapper: ArgsMapper<NA>? = null,
    private val routeNavigator: RouteNavigator,
) : SuperViewModel(), ActionDispatcher<A>, RouteNavigator by routeNavigator  {

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

        navArgs.update { argsMapper.argsFrom(map) }
    }
}
