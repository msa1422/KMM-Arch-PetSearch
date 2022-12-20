package com.msa.petsearch.shared.core.util.commonflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CommonStateFlow<T>(private val origin: StateFlow<T>) : StateFlow<T> by origin {

    @Suppress("Unused") // Called from Swift
    fun watch(block: (T) -> Unit) = Job().run job@{
        onEach(block).launchIn(CoroutineScope(Dispatchers.Main + this@job))

        object : Closeable {
            override fun close() = this@job.cancel()
        }
    }
}

fun <T> StateFlow<T>.asCommonStateFlow(): CommonStateFlow<T> = CommonStateFlow(this)
