package com.msa.petsearch.android.activity

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.msa.petsearch.android.activity.composable.MainSnackbarHost
import com.msa.petsearch.android.activity.composable.rememberSnackBarHostState
import com.msa.petsearch.android.activity.di.AppScreens
import com.msa.petsearch.android.common.compose.provide
import com.msa.petsearch.android.common.compose.theme.ApplicationTheme
import com.msa.petsearch.shared.core.util.resource.MessageType
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainContent() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberAnimatedNavController()
    val snackbarHostState = rememberSnackBarHostState()

    ApplicationTheme {
        Scaffold(
            snackbarHost = { MainSnackbarHost(snackbarHostState) },
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedNavHost(navController, NavigationScreen.HomeNavScreen.route) {
                AppScreens.provide(this@AnimatedNavHost, navController) {
                    onMessageReceived(
                        message = it,
                        snackbarHostState = snackbarHostState,
                        coroutineScope = coroutineScope,
                        context = context
                    )
                }
            }
        }
    }
}

private fun onMessageReceived(
    message: ResourceMessage,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    context: Context
) {
    when (val component = message.messageType) {
        is MessageType.SnackBar -> {
            message.text?.let {
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = it,
                        actionLabel = component.action,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                    when (result) {
                        SnackbarResult.Dismissed -> {
                            component.onDismiss.invoke()
                            message.dequeueCallback.invoke()
                        }
                        SnackbarResult.ActionPerformed -> {
                            component.onAction.invoke()
                            message.dequeueCallback.invoke()
                        }
                    }
                }
            }
        }

        is MessageType.Toast -> {
            Toast.makeText(context, message.text, Toast.LENGTH_LONG).show()
        }

        else -> {
            // Ignore this block
            // MessageType could be .None
            // or message could be null
        }
    }
}
