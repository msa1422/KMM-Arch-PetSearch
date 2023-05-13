package com.msa.petsearch.android.activity

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.msa.petsearch.android.activity.composable.MainSnackbarHost
import com.msa.petsearch.android.activity.composable.rememberSnackBarHostState
import com.msa.petsearch.android.activity.di.AppScreens
import com.msa.petsearch.android.common.compose.provide
import com.msa.petsearch.android.common.compose.theme.ApplicationTheme
import com.msa.petsearch.shared.core.util.resource.MessageDeque
import com.msa.petsearch.shared.core.util.resource.MessageType
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainContent() {
    val context = LocalContext.current
    val navController = rememberAnimatedNavController()
    val snackbarHostState = rememberSnackBarHostState()

    ApplicationTheme {
        Scaffold(
            snackbarHost = { MainSnackbarHost(snackbarHostState) },
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedNavHost(navController, NavigationScreen.HomeNavScreen.route) {
                AppScreens.provide(this@AnimatedNavHost, navController)
            }
        }
    }

    LaunchedEffect(Unit) {
        MessageDeque().collectLatest {
            onMessageReceived(it, snackbarHostState, context)
        }
    }
}

private suspend fun onMessageReceived(
    message: ResourceMessage,
    snackbarHostState: SnackbarHostState,
    context: Context
) {
    when (val component = message.messageType) {
        is MessageType.SnackBar -> {
            message.text?.let {
                val result = snackbarHostState.showSnackbar(
                    message = it,
                    actionLabel = component.actionLabel,
                    withDismissAction = true,
                    duration = SnackbarDuration.Short
                )

                when (result) {
                    SnackbarResult.Dismissed -> {
                        component.onDismiss.invoke()
                        MessageDeque.dequeue()
                    }
                    SnackbarResult.ActionPerformed -> {
                        component.onAction.invoke()
                        MessageDeque.dequeue()
                    }
                }
            }
        }

        is MessageType.Toast -> {
            Toast.makeText(context, message.text, Toast.LENGTH_LONG).show()

            delay(timeMillis = 4_000)
            MessageDeque.dequeue()
        }

        else -> {
            // Ignore this block
            // MessageType could be .None
            // or message could be null
        }
    }
}
