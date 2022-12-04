package com.msa.petsearch.shared.coreutil.commonflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CommonStateFlow<T>(private val origin: StateFlow<T>) : StateFlow<T> by origin {

    @Suppress("Unused") // Called from Swift
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

fun <T> StateFlow<T>.asCommonStateFlow(): CommonStateFlow<T> = CommonStateFlow(this)
