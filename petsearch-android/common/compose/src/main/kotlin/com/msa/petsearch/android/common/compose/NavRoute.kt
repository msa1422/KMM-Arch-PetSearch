package com.msa.petsearch.android.common.compose

import androidx.compose.animation.AnimatedContentTransitionScope
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
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.NavigateAndPopUpToRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.NavigateToRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.NavigateUp
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent.PopToRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen.Companion.fullRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator

typealias AnimatedBackStack = AnimatedContentTransitionScope<NavBackStackEntry>

/**
 * Heavily modified implementation of ViewModelNavigationCompose by Frank
 * [Source](https://github.com/Frank1234/ViewModelNavigationCompose)
 */
interface NavRoute<T : RouteNavigator> {
    val route: NavigationScreen

    @Composable
    fun Content(viewModel: T)

    val viewModel: T
        @Composable get

    val deepLinks: List<NavDeepLink>
        get() = emptyList()

    val enterTransition: (AnimatedBackStack.() -> EnterTransition?)?
        get() = null

    val exitTransition: (AnimatedBackStack.() -> ExitTransition?)?
        get() = null

    val popEnterTransition: (AnimatedBackStack.() -> EnterTransition?)?
        get() = enterTransition

    val popExitTransition: (AnimatedBackStack.() -> ExitTransition?)?
        get() = exitTransition

    fun asComposable(builder: NavGraphBuilder, navController: NavHostController) =
        builder.composable(
            route = route.fullRoute,
            arguments = getArguments(),
            deepLinks = deepLinks,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
        ) {
            with(viewModel) {
                Content(this)

                LaunchedEffect(Unit) {
                    navigationEvent.collect { event ->
                        onNavEvent(navController, event)
                    }
                }
            }
        }

    private fun getArguments(): List<NamedNavArgument> = route.args.map {
        navArgument(it) { type = NavType.StringType }
    }

    private fun onNavEvent(controller: NavHostController, event: NavigationEvent) =
        when (event) {
            is NavigateToRoute -> handleNavToRoute(controller, event)
            is NavigateAndPopUpToRoute -> onNavAndPopUpToRoute(controller, event)
            is PopToRoute -> onPopToRoute(controller, event)
            is NavigateUp -> onNavigateUp(controller)
        }
}

fun Iterable<NavRoute<*>>.provide(builder: NavGraphBuilder, navController: NavHostController) =
    forEach { it.asComposable(builder, navController) }

private fun handleNavToRoute(controller: NavHostController, event: NavigateToRoute) {
    if (controller.currentDestination?.route == event.route) {
        return
    }

    val currentRoute = event.args.joinArgs(event.route)
    controller.navigate(currentRoute) {
        launchSingleTop = true
        restoreState = true
        popUpTo(controller.graph.findStartDestination().id) {
            saveState = true
        }
    }
}

private fun onNavAndPopUpToRoute(controller: NavHostController, event: NavigateAndPopUpToRoute) {
    if (controller.currentDestination?.route == event.route) {
        return
    }

    val currentRoute = event.args.joinArgs(event.route)
    controller.navigate(currentRoute) {
        launchSingleTop = true
        restoreState = true
        popUpTo(event.popUpTo) {
            inclusive = true
            saveState = true
        }
    }
}

private fun onPopToRoute(controller: NavHostController, event: PopToRoute) {
    if (controller.currentDestination?.route == event.staticRoute) {
        return
    }

    controller.getBackStackEntry(event.staticRoute).arguments?.let { bundle ->
        event.args?.forEach { (key, value) ->
            bundle.putString(key, value)
        }
    }

    controller.popBackStack(event.staticRoute, false)
}

private fun onNavigateUp(controller: NavHostController) {
    controller.currentDestination?.route?.let {
        controller.popBackStack(route = it, inclusive = true)
    }
}

private fun HashMap<String, String>?.joinArgs(route: String): String {
    return this?.entries?.foldIndexed(route) { index, currentRoute, (key, value) ->
        val separator = if (index == 0) "/?" else "&"
        "$currentRoute$separator$key=${value.ifBlank { "null" }}"
    } ?: route
}
