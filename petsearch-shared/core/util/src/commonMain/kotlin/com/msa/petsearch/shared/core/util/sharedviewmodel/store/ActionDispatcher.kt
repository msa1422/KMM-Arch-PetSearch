package com.msa.petsearch.shared.core.util.sharedviewmodel.store

internal interface ActionDispatcher<A : NanoRedux.Action> {
    fun action(action: A)
}
