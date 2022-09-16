package com.msa.petsearch.petdetail

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
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
}
