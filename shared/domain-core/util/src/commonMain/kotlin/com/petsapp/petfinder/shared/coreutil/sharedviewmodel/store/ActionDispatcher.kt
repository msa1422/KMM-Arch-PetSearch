package com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store

internal interface ActionDispatcher<A : NanoRedux.Action> {
    suspend fun action(action: A)
}
