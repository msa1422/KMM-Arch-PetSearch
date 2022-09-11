package com.petsapp.petfinder.shared.core_util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// Credit - https://github.com/JetBrains/kotlinconf-app/blob/master/common/src/mobileMain/kotlin/org/jetbrains/kotlinconf/FlowUtils.kt
// Wrapper to consume Flow based API from Obj-C/Swift
// Alternatively we can use the 'Kotlinx_coroutines_coreFlowCollector' protocol from Swift as demonstrated in https://stackoverflow.com/a/66030092
// however the below wrapper gives us more control and hides the complexity in the shared Kotlin code.
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach { block(it) }
            .launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}

// Helper extension
fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

// Remove when Kotlin's Closeable is supported in K/N https://youtrack.jetbrains.com/issue/KT-31066
// Alternatively use Ktor Closeable which is K/N ready.
// To avoid the dependency of Ktor, this interface is being used
interface Closeable {
    fun close()
}
