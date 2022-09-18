package com.msa.petsearch.shared.coreutil.sharedviewmodel.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Main
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
internal actual val DispatcherProvider: CoroutineDispatcher = Main