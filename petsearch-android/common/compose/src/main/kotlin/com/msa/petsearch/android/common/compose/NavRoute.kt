package com.msa.petsearch.android.common.compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.msa.petsearch.android.common.compose.util.OnDestroy
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.NavigateAndPopUpToRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.NavigateToRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.NavigateUp
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.PopToRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import kotlinx.coroutines.delay
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

                LaunchedEffect(it) {
                    if (getArguments().isNotEmpty()) {
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

                    it.navigationEvent.collect { state ->
                        onNavEvent(navController, state)
                    }
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

    private suspend fun onNavEvent(controller: NavHostController, event: NavigationEvent) =
        when (event) {
            is NavigateToRoute -> handleNavigateToRoute(controller, event)
            is NavigateAndPopUpToRoute -> handleNavigateAndPopUpToRoute(controller, event)
            is PopToRoute -> handlePopToRoute(controller, event)
            is NavigateUp -> handleNavigateUp(controller, event)
        }
}

fun Iterable<NavRoute<*>>.provide(
    builder: NavGraphBuilder,
    navController: NavHostController,
    messenger: (ResourceMessage) -> Unit
) = forEach { it.asComposable(builder, navController, messenger) }

private suspend fun handleNavigateToRoute(
    navController: NavHostController, navigationState: NavigateToRoute
) {
    if (navController.currentDestination?.route == navigationState.route) {
        return
    }

    delay(navigationState.delay)

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
}

private suspend fun handleNavigateAndPopUpToRoute(
    navController: NavHostController, navigationState: NavigateAndPopUpToRoute
) {
    if (navController.currentDestination?.route == navigationState.route) {
        return
    }

    delay(navigationState.delay)

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
}

private suspend fun handlePopToRoute(
    navController: NavHostController, navigationState: PopToRoute
) {
    if (navController.currentDestination?.route == navigationState.staticRoute) {
        return
    }

    delay(navigationState.delay)

    navController
        .getBackStackEntry(navigationState.staticRoute)
        .arguments?.let { bundle ->
            navigationState.args?.forEach { args ->
                bundle.putString(args.key, args.value)
            }
        }

    navController.popBackStack(navigationState.staticRoute, false)
}

private suspend fun handleNavigateUp(
    navController: NavHostController, navigationState: NavigateUp
) {
    delay(navigationState.delay)

    navController.currentDestination?.route?.let {
        navController.popBackStack(route = it, inclusive = true)
    }
}
