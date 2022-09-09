package com.petsapp.petfinder.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.petsapp.petfinder.activity.composable.MainSnackbarHost
import com.petsapp.petfinder.activity.composable.rememberSnackBarHostState
import com.petsapp.petfinder.activity.di.provideAppScreens
import com.petsapp.petfinder.activity.util.setFullScreenContent
import com.petsapp.petfinder.common_compose.add
import com.petsapp.petfinder.common_compose.theme.ApplicationTheme
import com.petsapp.petfinder.shared.core_util.resource.MessageType
import com.petsapp.petfinder.shared.core_util.resource.ResourceMessage
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationScreen.HomeNavScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
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