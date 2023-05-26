package com.msa.petsearch.shared.core.util.extension

import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Action
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Event
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Navigation

@Suppress("unused") // Used in iOS
fun BaseViewModel<out Action, out Navigation, out Event>.put(args: Map<String, String>) =
    args.forEach { (key, value) ->
        savedStateHandle[key] = value
    }
