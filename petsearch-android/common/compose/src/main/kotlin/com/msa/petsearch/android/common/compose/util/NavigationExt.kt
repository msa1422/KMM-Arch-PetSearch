package com.msa.petsearch.android.common.compose.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavBackStackEntry

@Composable
fun NavBackStackEntry.OnDestroy(block: () -> Unit) {
    var hasActiveObservers by remember { mutableStateOf(false) }

    // Add an observer to Lifecycle of the CurrentDestination
    // ViewModel will be cleared when the backStackEntry is Destroyed (not when disposed)
    if (!hasActiveObservers && lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
        hasActiveObservers = true
        this.lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        block.invoke()
                        this@OnDestroy.lifecycle.removeObserver(this)
                        hasActiveObservers = false
                    }
                }
            }
        )
    }
}
