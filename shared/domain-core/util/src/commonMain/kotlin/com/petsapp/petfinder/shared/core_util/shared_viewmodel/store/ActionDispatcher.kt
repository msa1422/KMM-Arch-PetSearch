package com.petsapp.petfinder.shared.core_util.shared_viewmodel.store

internal interface ActionDispatcher<A : NanoRedux.Action> {
    suspend fun action(action: A)
}