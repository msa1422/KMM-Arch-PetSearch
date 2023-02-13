package com.msa.petsearch.shared.core.util.sharedviewmodel.contract

interface ActionDispatcher<A : UiContract.Action> {
    fun dispatch(action: A)
}
