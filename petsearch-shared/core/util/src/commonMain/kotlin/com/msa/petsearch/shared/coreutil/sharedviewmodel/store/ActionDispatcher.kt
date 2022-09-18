package com.msa.petsearch.shared.coreutil.sharedviewmodel.store

internal interface ActionDispatcher<A : NanoRedux.Action> {
    suspend fun action(action: A)
}
