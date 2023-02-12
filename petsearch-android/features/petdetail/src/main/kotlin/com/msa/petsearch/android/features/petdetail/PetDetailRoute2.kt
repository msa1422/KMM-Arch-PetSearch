package com.msa.petsearch.android.features.petdetail

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.msa.petsearch.android.common.compose.NavRoute
import com.msa.petsearch.android.common.compose.NavRoute2
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel2
import org.koin.androidx.compose.koinViewModel

object PetDetailRoute2 : NavRoute2<PetDetailViewModel2> {

    override val route: String
        get() = NavigationScreen.PetDetailNavScreen.route

    @Composable
    override fun Content(viewModel: PetDetailViewModel2) = PetDetailScreen2(viewModel)

    @Composable
    override fun viewModel(): PetDetailViewModel2 = koinViewModel()

    override fun getArguments(): List<NamedNavArgument> =
        listOf(navArgument(ARG_PET_INFO) { type = NavType.StringType })

    override fun getEnterTransition() = PetDetailEnterTransition

    override fun getExitTransition() = PetDetailExitTransition
}