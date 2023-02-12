package com.msa.petsearch.android.features.petdetail

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.msa.petsearch.android.common.compose.AnimatedBackStack
import com.msa.petsearch.android.common.compose.NavRoute
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel2
import org.koin.androidx.compose.koinViewModel

object PetDetailRoute : NavRoute<PetDetailViewModel2> {

    override val route: String
        get() = NavigationScreen.PetDetailNavScreen.route

    @Composable
    override fun Content(viewModel: PetDetailViewModel2) = PetDetailScreen(viewModel)

    @Composable
    override fun viewModel(): PetDetailViewModel2 = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_PET_INFO) { type = NavType.StringType })

    override fun getEnterTransition() = PetDetailEnterTransition

    override fun getExitTransition() = PetDetailExitTransition
}

private val PetDetailEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    slideIntoContainer(
        towards = androidx.compose.animation.AnimatedContentScope.SlideDirection.Up,
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

private val PetDetailExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    slideOutOfContainer(
        towards = androidx.compose.animation.AnimatedContentScope.SlideDirection.Down,
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
