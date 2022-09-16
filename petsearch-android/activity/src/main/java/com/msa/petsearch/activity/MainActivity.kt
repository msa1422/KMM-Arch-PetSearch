package com.msa.petsearch.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.msa.petsearch.activity.composable.MainSnackbarHost
import com.msa.petsearch.activity.composable.rememberSnackBarHostState
import com.msa.petsearch.activity.di.provideAppScreens
import com.msa.petsearch.activity.util.setFullScreenContent
import com.msa.petsearch.commoncompose.add
import com.msa.petsearch.commoncompose.theme.ApplicationTheme
import com.msa.petsearch.shared.coreutil.resource.MessageType
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationScreen.HomeNavScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenContent {
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
                    AnimatedNavHost(navController, HomeNavScreen.route) {
                        provideAppScreens().add(this, navController) {
                            onMessageReceived(it, snackbarHostState, coroutineScope)
                        }
                    }
                }
            }
        }
    }

    private fun onMessageReceived(
        message: ResourceMessage,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
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
                Toast.makeText(this, message.text, Toast.LENGTH_LONG).show()
            }

            else -> {
                // Ignore this block
                // MessageType could be .None
                // or message could be null
            }
        }
    }
}
