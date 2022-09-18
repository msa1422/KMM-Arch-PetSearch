package com.msa.petsearch.shared.coreutil.commonflow

import com.msa.petsearch.shared.coreutil.sharedviewmodel.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// Credit - https://github.com/JetBrains/kotlinconf-app/blob/master/common/src/mobileMain/kotlin/org/jetbrains/kotlinconf/FlowUtils.kt
// Wrapper to consume Flow based API from Obj-C/Swift
// Alternatively we can use the 'Kotlinx_coroutines_coreFlowCollector' protocol from Swift as demonstrated in https://stackoverflow.com/a/66030092
// however the below wrapper gives us more control and hides the complexity in the shared Kotlin code.
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    @Suppress("Unused") // Called from Swift
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach { block(it) }
            .launchIn(CoroutineScope(DispatcherProvider + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}


