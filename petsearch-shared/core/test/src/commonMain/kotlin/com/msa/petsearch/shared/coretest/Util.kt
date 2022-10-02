package com.msa.petsearch.shared.coretest

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

expect fun suspendTest(block: suspend CoroutineScope.() -> Unit)

expect val testCoroutineContext: CoroutineContext

expect val testCoroutineScope: CoroutineScope

expect val testCoroutineDispatcher: CoroutineDispatcher
