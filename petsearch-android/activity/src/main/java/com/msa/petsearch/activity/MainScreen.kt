package com.msa.petsearch.activity

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.msa.petsearch.activity.composable.MainSnackbarHost
import com.msa.petsearch.activity.composable.rememberSnackBarHostState
import com.msa.petsearch.activity.di.AppScreens
import com.msa.petsearch.commoncompose.provide
import com.msa.petsearch.shared.coreutil.resource.MessageType
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationScreen
import kotlinx.coroutines.launch

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MainScreen() {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberAnimatedNavController()
    val snackbarHostState = rememberSnackBarHostState()

    val onMessageReceived: (ResourceMessage) -> Unit = remember {
        { message: ResourceMessage ->
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
    }

    Scaffold(
        snackbarHost = { MainSnackbarHost(snackbarHostState) },
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AnimatedNavHost(navController, NavigationScreen.HomeNavScreen.route) {
            AppScreens.provide(this@AnimatedNavHost, navController) {
                onMessageReceived(it)
            }
        }
    }
}
