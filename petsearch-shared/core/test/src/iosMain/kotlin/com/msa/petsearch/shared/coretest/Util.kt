@file:OptIn(ExperimentalCoroutinesApi::class)

package com.msa.petsearch.shared.coretest

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

actual fun suspendTest(block: suspend CoroutineScope.() -> Unit) =
    runBlocking(testCoroutineContext) { this.block() }

actual val testCoroutineContext: CoroutineContext = newSingleThreadContext("testRunner")

actual val testCoroutineScope: CoroutineScope = CoroutineScope(testCoroutineContext)

actual val testCoroutineDispatcher: CoroutineDispatcher = newSingleThreadContext("testRunner")
