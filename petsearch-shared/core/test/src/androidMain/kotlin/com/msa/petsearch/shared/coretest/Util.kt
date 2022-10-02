package com.msa.petsearch.shared.coretest

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

actual fun suspendTest(block: suspend CoroutineScope.() -> Unit) =
    runBlocking(testCoroutineContext) { this.block() }

actual val testCoroutineContext: CoroutineContext =
    Executors.newSingleThreadExecutor().asCoroutineDispatcher()

actual val testCoroutineScope: CoroutineScope = CoroutineScope(testCoroutineContext)

actual val testCoroutineDispatcher: CoroutineDispatcher =
    Executors.newSingleThreadExecutor().asCoroutineDispatcher()
