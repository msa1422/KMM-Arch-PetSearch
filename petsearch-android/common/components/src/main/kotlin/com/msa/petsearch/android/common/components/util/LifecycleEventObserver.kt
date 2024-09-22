package com.msa.petsearch.android.common.components.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun <T> T.LifecycleCallbackFor(
    vararg event: Lifecycle.Event,
    onEvent: (Lifecycle.Event) -> Unit
) where T : LifecycleOwner {
    var hasActiveObservers by remember { mutableStateOf(false) }
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(this)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle

        val observer = LifecycleEventObserver { _, e ->
            if (event.any { it == e }) {
                eventHandler.value(e)
            }
        }

        if (!hasActiveObservers) {
            lifecycle.addObserver(observer)
            hasActiveObservers = true
        }

        onDispose {
            hasActiveObservers = false
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun <T> T.Observe(
    owner: LifecycleOwner = LocalLifecycleOwner.current,
    block: () -> Unit
) where T : Lifecycle.Event {
    var hasActiveObservers by remember { mutableStateOf(false) }
    val eventHandler = rememberUpdatedState(block)
    val lifecycleOwner = rememberUpdatedState(owner)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle

        val observer = LifecycleEventObserver { _, e ->
            if (this@Observe == e) {
                eventHandler.value()
            }
        }

        if (!hasActiveObservers) {
            lifecycle.addObserver(observer)
            hasActiveObservers = true
        }

        onDispose {
            hasActiveObservers = false
            lifecycle.removeObserver(observer)
        }
    }
}
