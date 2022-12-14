package com.msa.petsearch.shared.core.util.sharedviewmodel.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.native.internal.GC

actual open class SuperViewModel actual constructor() {

    protected actual val viewModelScope: CoroutineScope = MainScope()

    protected actual open fun onCleared() {
    }

    @Suppress("Unused") // Called from Swift
    fun clear() {
        onCleared()
        dispatch_async(dispatch_get_main_queue()) { GC.collect() }
    }
}
