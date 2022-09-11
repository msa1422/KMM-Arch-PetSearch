package com.petsapp.petfinder.pet_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.petsapp.petfinder.common_compose.NavRoute
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.ARG_PET_INFO
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationScreen
import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.PetDetailViewModel
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
