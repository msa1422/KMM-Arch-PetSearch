package com.msa.petsearch.android.features.home

import androidx.compose.runtime.Composable
import com.msa.petsearch.android.common.compose.NavRoute2
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.ui.home.HomeViewModel2
import org.koin.androidx.compose.koinViewModel

object HomeNavRoute2 : NavRoute2<HomeViewModel2> {

    override val route: String
        get() = NavigationScreen.HomeNavScreen.route

    @Composable
    override fun Content(viewModel: HomeViewModel2) = HomeScreen2(viewModel)

    @Composable
    override fun viewModel(): HomeViewModel2 = koinViewModel()

    override fun getPopEnterTransition() = HomePopEnterTransition

    override fun getPopExitTransition() = HomePopExitTransition
}