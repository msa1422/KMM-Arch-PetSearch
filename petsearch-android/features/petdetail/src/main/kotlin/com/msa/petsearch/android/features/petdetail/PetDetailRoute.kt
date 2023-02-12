package com.msa.petsearch.android.features.petdetail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
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
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.androidx.compose.koinViewModel

object PetDetailRoute : NavRoute<PetDetailViewModel> {

    override val route: String
        get() = NavigationScreen.PetDetailNavScreen.route

    @Composable
    override fun Content(viewModel: PetDetailViewModel) = PetDetailScreen(viewModel)

    @Composable
    override fun viewModel(): PetDetailViewModel = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_PET_INFO) { type = NavType.StringType })

    override fun getEnterTransition() = PetDetailEnterTransition

    override fun getExitTransition() = PetDetailExitTransition
}

val PetDetailEnterTransition: AnimatedBackStack.() -> EnterTransition? = {
    slideIntoContainer(
        towards = AnimatedContentScope.SlideDirection.Up,
        animationSpec = tween(
            durationMillis = 290,
            delayMillis = 10,
            easing = FastOutSlowInEasing
        ),
        initialOffset = @Suppress("MagicNumber") { it / 4 }
    )
        .plus(
            fadeIn(
                animationSpec = tween(
                    durationMillis = 150,
                    delayMillis = 10,
                    easing = FastOutSlowInEasing
                )
            )
        )
}

val PetDetailExitTransition: AnimatedBackStack.() -> ExitTransition? = {
    slideOutOfContainer(
        towards = AnimatedContentScope.SlideDirection.Down,
        animationSpec = tween(
            durationMillis = 280,
            delayMillis = 20,
            easing = FastOutSlowInEasing
        ),
        targetOffset = @Suppress("MagicNumber") { it / 4 }
    )
        .plus(
            fadeOut(
                animationSpec = tween(
                    durationMillis = 280,
                    delayMillis = 20,
                    easing = FastOutSlowInEasing
                )
            )
        )
}
