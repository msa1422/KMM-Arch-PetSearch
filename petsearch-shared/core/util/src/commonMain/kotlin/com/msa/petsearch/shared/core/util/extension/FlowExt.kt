package com.msa.petsearch.shared.core.util.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

fun <T> Flow<T>.stateInWhenSubscribed(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
    initialValue: T
) = stateIn(scope = scope, started = started, initialValue = initialValue)
