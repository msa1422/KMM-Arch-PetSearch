package com.msa.petsearch.shared.core.util.sharedviewmodel.store

interface Processor<SE : NanoRedux.SideEffect, out A : NanoRedux.Action> {
    /**
     * Dispatches new side effects, and returns an action that may contain the results of the effect
     * the returned action will modify the state like any other action dispatched from the view layer
     *
     * Exception handling: the implementation of this method should handle exceptions, otherwise it will be propagated
     * to the [kotlinx.coroutines.CoroutineExceptionHandler] of the [kotlin.coroutines.CoroutineContext] if it was set
     */
    suspend fun dispatchSideEffect(effect: SE): A

    fun close()
}
