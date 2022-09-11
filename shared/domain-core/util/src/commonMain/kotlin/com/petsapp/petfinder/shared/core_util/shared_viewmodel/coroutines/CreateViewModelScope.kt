package com.petsapp.petfinder.shared.core_util.shared_viewmodel.coroutines

import com.petsapp.petfinder.shared.core_util.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
internal val createViewModelScope: () -> CoroutineScope = {
    CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}

internal class CloseableCoroutineScope(context: CoroutineContext) : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = context

    override fun close() {
        coroutineContext.cancel()
    }
}
