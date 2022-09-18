package com.msa.petsearch.shared.coreutil.sharedviewmodel.util

import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.native.internal.GC

actual abstract class SuperViewModel actual constructor() {

    // Not required in this architecture
    // protected actual val viewModelScope: CoroutineScope = MainScope

    protected actual open fun onCleared() {
    }

    @Suppress("Unused") // Called from Swift
    fun clear() {
        onCleared()
        dispatch_async(dispatch_get_main_queue()) { GC.collect() }
    }
}
