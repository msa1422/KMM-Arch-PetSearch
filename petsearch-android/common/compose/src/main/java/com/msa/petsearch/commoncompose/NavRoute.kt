package com.msa.petsearch.commoncompose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.composable
import com.msa.petsearch.commoncompose.util.HandyDelay
import com.msa.petsearch.commoncompose.util.collectAsStateWithLifecycle
import com.msa.petsearch.commoncompose.util.onDestroy
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationState
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import kotlinx.coroutines.flow.collectLatest

/**
 * Heavily modified implementation of ViewModelNavigationCompose by Frank
 * Source - https://github.com/Frank1234/ViewModelNavigationCompose
 *
 */
typealias AnimatedBackStack = AnimatedContentScope<NavBackStackEntry>

interface NavRoute<T : RouteNavigator> {

    val route: String

    val enableLifecycleObserver: Boolean
        get() = true

    @Composable
    fun content(viewModel: T)

    @Composable
    fun viewModel(entry: NavBackStackEntry): T

    fun getArguments(): List<NamedNavArgument> = emptyList()

    fun getDeepLinks(): List<NavDeepLink> = emptyList()

    fun getEnterTransition(): (AnimatedBackStack.() -> EnterTransition?)? = null

    fun getExitTransition(): (AnimatedBackStack.() -> ExitTransition?)? = null

    fun getPopEnterTransition(): (AnimatedBackStack.() -> EnterTransition?)? = getEnterTransition()

    fun getPopExitTransition(): (AnimatedBackStack.() -> ExitTransition?)? = getExitTransition()

    fun asComposable(
        builder: NavGraphBuilder,
        navController: NavHostController,
        messenger: (ResourceMessage) -> Unit
    ) {
        builder.composable(
            route = route,
            arguments = getArguments(),
            deepLinks = getDeepLinks(),
            enterTransition = getEnterTransition(),
            exitTransition = getExitTransition(),
            popEnterTransition = getPopEnterTransition(),
            popExitTransition = getPopExitTransition()
        ) { backStackEntry ->
            val viewModel = viewModel(backStackEntry)

            (viewModel as? BaseViewModel<*, *, *, *, *, *, *>)?.let {
                if (enableLifecycleObserver) {
                    backStackEntry.onDestroy {
                        it.onCleared()
                    }
                }

                val navigationState by it.observeNavigationState()
                    .collectAsStateWithLifecycle(initialValue = NavigationState.Idle)

                LaunchedEffect(navigationState) {
                    updateNavigationState(navController, navigationState, it::onNavComplete)

                    // .............................................................................
                    // Update Args in ViewModel
                    val argsMap = hashMapOf<String, String>()

                    getArguments().forEach { namedArg ->
                        backStackEntry.arguments
                            ?.getString(namedArg.name)
                            ?.let { arg ->
                                argsMap[namedArg.name] = arg
                            }
                    }

                    it.updateArgsInState(argsMap)
                }

                LaunchedEffect(it) {
                    it.observeMessageDeque().collectLatest { message ->
                        if (message != null) {
                            messenger.invoke(message)
                        }
                    }
                }

                content(viewModel)
            }
        }
    }

    private fun updateNavigationState(
        navController: NavHostController,
        navigationState: NavigationState,
        onComplete: (NavigationState) -> Unit
    ) {
        try {
            when (navigationState) {
                is NavigationState.NavigateToRoute -> {
                    if (navController.currentDestination?.route != navigationState.route) {
                        HandyDelay.with(duration = navigationState.delay) {
                            var currentRoute = navigationState.route

                            navigationState.args?.forEach { entry ->
                                currentRoute = currentRoute
                                    .replace(
                                        oldValue = "{${entry.key}}",
                                        newValue = entry.value.takeIf { it.isNotBlank() } ?: "null"
                                    )
                            }

                            navController.navigate(currentRoute) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                            onComplete(navigationState)
                        }
                    }
                }

                is NavigationState.NavigateAndPopUpToRoute -> {
                    if (navController.currentDestination?.route != navigationState.route) {
                        HandyDelay.with(duration = navigationState.delay) {
                            var currentRoute = navigationState.route

                            navigationState.args?.forEach { args ->
                                currentRoute = currentRoute
                                    .replace(
                                        oldValue = "{${args.key}}",
                                        newValue = args.value.takeIf { it.isNotBlank() } ?: "null"
                                    )
                            }

                            navController.navigate(currentRoute) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navigationState.popUpTo) {
                                    inclusive = true
                                    saveState = true
                                }
                            }
                            onComplete(navigationState)
                        }
                    }
                }

                is NavigationState.PopToRoute -> {
                    if (navController.currentDestination?.route != navigationState.staticRoute) {
                        HandyDelay.with(duration = navigationState.delay) {
                            navController
                                .getBackStackEntry(navigationState.staticRoute)
                                .arguments?.let { bundle ->
                                    navigationState.args?.forEach { args ->
                                        bundle.putString(args.key, args.value)
                                    }
                                }

                            navController.popBackStack(navigationState.staticRoute, false)
                            onComplete(navigationState)
                        }
                    }
                }

                is NavigationState.NavigateUp -> {
                    HandyDelay.with(duration = navigationState.delay) {
                        navController.currentDestination?.route?.let {
                            navController.popBackStack(route = it, inclusive = true)
                            onComplete(navigationState)
                        }
                    }
                }

                is NavigationState.Idle -> {}
            }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            onComplete(navigationState)
        }
    }
}

fun Iterable<NavRoute<*>>.provide(
    builder: NavGraphBuilder,
    navController: NavHostController,
    messenger: (ResourceMessage) -> Unit
) {
    forEach {
        it.asComposable(builder, navController, messenger)
    }
}
