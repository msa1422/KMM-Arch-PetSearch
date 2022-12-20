package com.msa.petsearch.shared.core.util.commonflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// Credit - https://github.com/JetBrains/kotlinconf-app/blob/master/common/src/mobileMain/kotlin/org/jetbrains/kotlinconf/FlowUtils.kt
// Wrapper to consume Flow based API from Obj-C/Swift
// Alternatively we can use the 'Kotlinx_coroutines_coreFlowCollector' protocol from Swift as demonstrated in https://stackoverflow.com/a/66030092
// however the below wrapper gives us more control and hides the complexity in the shared Kotlin code.
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {

    @Suppress("Unused") // Called from Swift
    fun watch(block: (T) -> Unit) = Job().run job@ {
        onEach(block).launchIn(CoroutineScope(Main + this@job))

        object : Closeable {
            override fun close() = this@job.cancel()
        }
    }
}

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

fun <T> commonFlowOf(): CommonFlow<T> = flowOf<T>().asCommonFlow()
