package com.petsapp.petfinder.commoncompose.util

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun <T> T.lifecycleCallbackFor(
    vararg event: Lifecycle.Event,
    onEvent: (Lifecycle.Event) -> Unit
) where T : LifecycleOwner {
    val hasActiveObservers = remember { mutableStateOf(false) }
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(this)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle

        val observer = LifecycleEventObserver { _, e ->
            if (event.any { it == e }) {
                eventHandler.value(e)
            }
        }

        if (!hasActiveObservers.value) {
            lifecycle.addObserver(observer)
            hasActiveObservers.value = true
        }

        onDispose {
            hasActiveObservers.value = false
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun <T> T.observe(
    owner: LifecycleOwner = LocalLifecycleOwner.current,
    block: () -> Unit
) where T : Lifecycle.Event {
    val hasActiveObservers = remember { mutableStateOf(false) }
    val eventHandler = rememberUpdatedState(block)
    val lifecycleOwner = rememberUpdatedState(owner)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle

        val observer = LifecycleEventObserver { _, e ->
            if (this@observe == e) {
                eventHandler.value()
            }
        }

        if (!hasActiveObservers.value) {
            lifecycle.addObserver(observer)
            hasActiveObservers.value = true
        }

        onDispose {
            hasActiveObservers.value = false
            lifecycle.removeObserver(observer)
        }
    }
}
