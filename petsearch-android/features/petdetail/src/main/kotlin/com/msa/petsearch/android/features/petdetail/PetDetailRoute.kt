package com.msa.petsearch.android.features.petdetail

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import com.msa.petsearch.android.common.compose.AnimatedBackStack
import com.msa.petsearch.android.common.compose.NavRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.androidx.compose.koinViewModel

object PetDetailRoute : NavRoute<PetDetailViewModel> {
    override val route: NavigationScreen
        get() = NavigationScreen.PetDetailNavScreen

    @Composable
    override fun Content(viewModel: PetDetailViewModel) = PetDetailScreen(viewModel)

    override val viewModel: PetDetailViewModel
        @Composable get() = koinViewModel()

    override val enterTransition
        get() = petDetailEnterTransition

    override val exitTransition
        get() = petDetailExitTransition
}

private val petDetailEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(
            durationMillis = 290,
            delayMillis = 10,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ),
        initialOffset = @Suppress("MagicNumber") { it / 4 }
    )
        .plus(
            fadeIn(
                animationSpec = tween(
                    durationMillis = 150,
                    delayMillis = 10,
                    easing = androidx.compose.animation.core.FastOutSlowInEasing
                )
            )
        )
}

private val petDetailExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(
            durationMillis = 280,
            delayMillis = 20,
            easing = androidx.compose.animation.core.FastOutSlowInEasing
        ),
        targetOffset = @Suppress("MagicNumber") { it / 4 }
    )
        .plus(
            fadeOut(
                animationSpec = tween(
                    durationMillis = 280,
                    delayMillis = 20,
                    easing = androidx.compose.animation.core.FastOutSlowInEasing
                )
            )
        )
}
