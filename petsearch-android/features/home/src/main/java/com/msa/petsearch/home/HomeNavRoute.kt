package com.msa.petsearch.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.msa.petsearch.commoncompose.NavRoute
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.domain.homeuicontract.HomeViewModel
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
