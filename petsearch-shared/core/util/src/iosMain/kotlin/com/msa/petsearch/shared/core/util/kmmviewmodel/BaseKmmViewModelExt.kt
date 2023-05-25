package com.msa.petsearch.shared.core.util.kmmviewmodel

import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Action as Ac
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Event as Ev
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract.Navigation as Na

fun <A : Ac, N : Na, E : Ev> BaseKmmViewModel<A, N, E>.putArgs(map: HashMap<String, String>) =
    map.forEach { (key, value) ->
        savedStateHandle[key] = value
    }
