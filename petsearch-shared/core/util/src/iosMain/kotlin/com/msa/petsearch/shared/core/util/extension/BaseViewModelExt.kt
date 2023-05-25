package com.msa.petsearch.shared.core.util.extension

import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Navigation

fun <A : Action, N : Navigation, E : Event> BaseViewModel<A, N, E>.put(args: Map<String, String>) =
    args.forEach { (key, value) ->
        savedStateHandle[key] = value
    }
