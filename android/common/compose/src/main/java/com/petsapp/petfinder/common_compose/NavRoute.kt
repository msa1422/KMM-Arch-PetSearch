package com.petsapp.petfinder.common_compose

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.petsapp.petfinder.common_compose.util.HandyDelay
import com.petsapp.petfinder.common_compose.util.onDestroy
import com.petsapp.petfinder.shared.core_util.resource.ResourceMessage
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.BaseViewModel
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationState
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.RouteNavigator
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.flow.collectLatest


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
                        it.onDestroy()
                    }
                }

                val navigationState by rememberFlowWithLifecycle(it.observeNavigationState())
                    .collectAsState(initial = NavigationState.Idle)

                LaunchedEffect(navigationState) {

                    updateNavigationState(navController, navigationState, it::onNavComplete)


                    //..........................................................................
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
        onComplete: (NavigationState) -> Unit,
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
        }
        catch (e: IllegalArgumentException) {
            e.printStackTrace()
            onComplete(navigationState)
        }
    }

}

fun Iterable<NavRoute<*>>.add(
    builder: NavGraphBuilder,
    navController: NavHostController,
    messenger: (ResourceMessage) -> Unit
) {
    forEach {
        it.asComposable(builder, navController, messenger)
    }
}
