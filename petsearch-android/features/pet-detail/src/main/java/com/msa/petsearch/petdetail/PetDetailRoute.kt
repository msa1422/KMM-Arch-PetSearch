package com.msa.petsearch.petdetail

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.msa.petsearch.commoncompose.AnimatedBackStack
import com.msa.petsearch.commoncompose.NavRoute
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.domain.petdetailuicontract.PetDetailViewModel
import org.koin.androidx.compose.koinViewModel

object PetDetailRoute : NavRoute<PetDetailViewModel> {

    override val route: String
        get() = NavigationScreen.PetDetailNavScreen.route

    @Composable
    override fun content(viewModel: PetDetailViewModel) =
        PetDetailScreen(viewModel)

    @Composable
    override fun viewModel(entry: NavBackStackEntry): PetDetailViewModel =
        koinViewModel(owner = { entry.viewModelStore })

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_PET_INFO) { type = NavType.StringType })

    override fun getEnterTransition(): (AnimatedBackStack.() -> EnterTransition?) = {
        slideIntoContainer(
            towards = AnimatedContentScope.SlideDirection.Up,
            animationSpec = tween(
                durationMillis = 290,
                delayMillis = 10,
                easing = FastOutSlowInEasing
            ),
            initialOffset = { it / 4 }
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

    override fun getExitTransition(): (AnimatedBackStack.() -> ExitTransition?) = {
        slideOutOfContainer(
            towards = AnimatedContentScope.SlideDirection.Down,
            animationSpec = tween(
                durationMillis = 280,
                delayMillis = 20,
                easing = FastOutSlowInEasing
            ),
            targetOffset = { it / 4 }
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
}
