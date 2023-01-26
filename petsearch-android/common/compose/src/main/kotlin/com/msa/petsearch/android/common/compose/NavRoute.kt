package com.msa.petsearch.android.common.compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.msa.petsearch.android.common.compose.util.HandyDelay
import com.msa.petsearch.android.common.compose.util.OnDestroy
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationState
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import kotlinx.coroutines.flow.collectLatest

typealias AnimatedBackStack = AnimatedContentScope<NavBackStackEntry>

/**
 * Heavily modified implementation of ViewModelNavigationCompose by Frank
 * [Source](https://github.com/Frank1234/ViewModelNavigationCompose)
 */
interface NavRoute<T : RouteNavigator> {

    val route: String

    val enableLifecycleObserver: Boolean
        get() = true

    @Composable
    fun Content(viewModel: T)

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

            (viewModel as? BaseViewModel<*, *, *, *, *, *>)?.let {
                if (enableLifecycleObserver) {
                    backStackEntry.OnDestroy(it::onCleared)
                }

                val navigationState by it.navigationState
                    .collectAsStateWithLifecycle(initialValue = NavigationState.Idle)

                LaunchedEffect(navigationState) {
                    updateNavigationState(navController, navigationState, it::onNavComplete)

                    // Update Args in ViewModel
                    val argsMap = hashMapOf<String, String>().also { hashMap ->
                        getArguments().forEach { namedArg ->
                            backStackEntry.arguments?.getString(namedArg.name)?.let { arg ->
                                hashMap[namedArg.name] = arg
                            }
                        }
                    }

                    it.updateArgsInState(argsMap)
                }

                LaunchedEffect(it) {
                    it.messageFlow.collectLatest { message ->
                        message?.let(messenger::invoke)
                    }
                }

                Content(viewModel)
            }
        }
    }

    private fun updateNavigationState(
        navController: NavHostController,
        navigationState: NavigationState,
        onComplete: (NavigationState) -> Unit
    ) {
        when (navigationState) {
            is NavigationState.NavigateToRoute ->
                handleNavigateToRoute(navController, navigationState, onComplete)

            is NavigationState.NavigateAndPopUpToRoute ->
                handleNavigateAndPopUpToRoute(navController, navigationState, onComplete)

            is NavigationState.PopToRoute ->
                handlePopToRoute(navController, navigationState, onComplete)

            is NavigationState.NavigateUp ->
                handleNavigateUp(navController, navigationState, onComplete)

            is NavigationState.Idle -> Unit
        }
    }
}

fun Iterable<NavRoute<*>>.provide(
    builder: NavGraphBuilder,
    navController: NavHostController,
    messenger: (ResourceMessage) -> Unit
) = forEach { it.asComposable(builder, navController, messenger) }

private fun handleNavigateToRoute(
    navController: NavHostController,
    navigationState: NavigationState.NavigateToRoute,
    onComplete: (NavigationState) -> Unit
) {
    if (navController.currentDestination?.route == navigationState.route) {
        return
    }

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

private fun handleNavigateAndPopUpToRoute(
    navController: NavHostController,
    navigationState: NavigationState.NavigateAndPopUpToRoute,
    onComplete: (NavigationState) -> Unit
) {
    if (navController.currentDestination?.route == navigationState.route) {
        return
    }

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

private fun handlePopToRoute(
    navController: NavHostController,
    navigationState: NavigationState.PopToRoute,
    onComplete: (NavigationState) -> Unit
) {
    if (navController.currentDestination?.route == navigationState.staticRoute) {
        return
    }

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

private fun handleNavigateUp(
    navController: NavHostController,
    navigationState: NavigationState.NavigateUp,
    onComplete: (NavigationState) -> Unit
) {
    HandyDelay.with(duration = navigationState.delay) {
        navController.currentDestination?.route?.let {
            navController.popBackStack(route = it, inclusive = true)
            onComplete(navigationState)
        }
    }
}
