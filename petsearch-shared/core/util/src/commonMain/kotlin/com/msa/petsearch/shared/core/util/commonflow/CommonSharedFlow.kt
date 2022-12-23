package com.msa.petsearch.shared.core.util.commonflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CommonSharedFlow<T>(private val origin: SharedFlow<T>) : SharedFlow<T> by origin {

    @Suppress("Unused") // Called from Swift
    fun watch(block: (T) -> Unit) = Job().run job@ {
        onEach { block(it) }.launchIn(CoroutineScope(Dispatchers.Main + this@job))

        object : Closeable {
            override fun close() = this@job.cancel()
        }
    }
}

fun <T> SharedFlow<T>.asCommonSharedFlow(): CommonSharedFlow<T> = CommonSharedFlow(this)
