package com.msa.petsearch.shared.coreutil.sharedviewmodel.store

internal interface ActionDispatcher<A : NanoRedux.Action> {
    fun action(action: A)
}
