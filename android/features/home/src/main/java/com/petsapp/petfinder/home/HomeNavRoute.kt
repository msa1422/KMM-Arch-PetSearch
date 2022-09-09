package com.petsapp.petfinder.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.petsapp.petfinder.common_compose.NavRoute
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationScreen
import com.petsapp.petfinder.shared.domain.home_ui_contract.HomeViewModel
import org.koin.androidx.compose.koinViewModel

object HomeNavRoute : NavRoute<HomeViewModel> {

    override val route: String
        get() = NavigationScreen.HomeNavScreen.route

    @Composable
    override fun content(viewModel: HomeViewModel) =
        HomeScreen(viewModel)

    @Composable
    override fun viewModel(entry: NavBackStackEntry): HomeViewModel =
        koinViewModel(owner = { entry.viewModelStore })

}