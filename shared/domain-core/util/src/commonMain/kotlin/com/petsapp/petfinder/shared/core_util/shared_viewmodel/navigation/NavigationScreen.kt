package com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation


sealed class NavigationScreen(val route: String) {

    object SplashNavScreen : NavigationScreen(SPLASH_DESTINATION)

    object HomeNavScreen : NavigationScreen(HOME_DESTINATION)

    object PetDetailNavScreen : NavigationScreen("$PET_DETAIL_DESTINATION/?$ARG_PET_INFO={$ARG_PET_INFO}")

}

const val NAV_PREFIX: String = "com.petsapp.petfinder"

const val SPLASH_DESTINATION: String = "$NAV_PREFIX.splash/SplashScreen"
const val HOME_DESTINATION: String = "$NAV_PREFIX.home/HomeScreen"
const val PET_DETAIL_DESTINATION: String = "$NAV_PREFIX.pet_detail/PetDetailScreen"

const val ARG_PET_INFO: String = "arg_pet_info"

