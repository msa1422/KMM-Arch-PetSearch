package com.msa.petsearch.android.features.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.msa.petsearch.android.common.compose.AnimatedBackStack
import com.msa.petsearch.android.common.compose.NavRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

object HomeNavRoute : NavRoute<HomeViewModel> {

    override val route: String
        get() = NavigationScreen.HomeNavScreen.route

    @Composable
    override fun Content(viewModel: HomeViewModel) = HomeScreen(viewModel)

    @Composable
    override fun viewModel(entry: NavBackStackEntry): HomeViewModel =
        koinViewModel(owner = { entry.viewModelStore })

    override fun getPopEnterTransition() = HomePopEnterTransition

    override fun getPopExitTransition() = HomePopExitTransition
}

private val HomePopEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    fadeIn(animationSpec = tween(durationMillis = 300))
}

private val HomePopExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    // Basically a MaterialHold
    fadeOut(
        animationSpec = tween(durationMillis = 300),
        targetAlpha = 1F
    )
}
