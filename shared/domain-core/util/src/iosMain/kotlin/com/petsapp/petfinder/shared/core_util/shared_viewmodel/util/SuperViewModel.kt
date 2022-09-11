package com.petsapp.petfinder.shared.core_util.shared_viewmodel.util

import com.petsapp.petfinder.shared.core_util.shared_viewmodel.coroutines.createViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.native.internal.GC

actual open class SuperViewModel actual constructor() {
    actual open val viewModelScope: CoroutineScope = createViewModelScope()
    actual open fun onDestroy() {
        viewModelScope.cancel()
        dispatch_async(dispatch_get_main_queue()) { GC.collect() }
    }
}
