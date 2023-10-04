package com.msa.petsearch.android.features.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.msa.petsearch.android.common.compose.AnimatedBackStack
import com.msa.petsearch.android.common.compose.NavRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

object HomeNavRoute : NavRoute<HomeViewModel> {
    override val route: NavigationScreen
        get() = NavigationScreen.HomeNavScreen

    @Composable
    override fun Content(viewModel: HomeViewModel) = HomeScreen(viewModel)

    override val viewModel: HomeViewModel
        @Composable get() = koinViewModel()

    override val popEnterTransition
        get() = homePopEnterTransition

    override val popExitTransition
        get() = homePopExitTransition
}

private val homePopEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    fadeIn(animationSpec = tween(durationMillis = 300))
}

private val homePopExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    // Basically a MaterialHold
    fadeOut(
        animationSpec = tween(durationMillis = 300),
        targetAlpha = 1F
    )
}
