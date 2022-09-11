package com.petsapp.petfinder.common_compose.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavBackStackEntry

@Composable
fun NavBackStackEntry.onDestroy(block: () -> Unit) {
    val hasActiveObservers = remember { mutableStateOf(false) }

    // Add an observer to Lifecycle of the CurrentDestination
    // ViewModel will be cleared when the backStackEntry is Destroyed (not when disposed)
    if (!hasActiveObservers.value && lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
        hasActiveObservers.value = true
        this.lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        block.invoke()
                        this@onDestroy.lifecycle.removeObserver(this)
                        hasActiveObservers.value = false
                    }
                }
            }
        )
    }
}
