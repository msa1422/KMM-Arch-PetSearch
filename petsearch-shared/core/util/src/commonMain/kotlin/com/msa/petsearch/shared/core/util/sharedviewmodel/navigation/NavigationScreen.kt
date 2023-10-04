package com.msa.petsearch.shared.core.util.sharedviewmodel.navigation

enum class NavigationScreen(val route: String, vararg val args: String) {
    SplashNavScreen(route = SPLASH_DESTINATION),
    HomeNavScreen(route = HOME_DESTINATION),
    PetDetailNavScreen(route = PET_DETAIL_DESTINATION, ARG_PET_INFO);

    companion object {
        val NavigationScreen.fullRoute: String
            get() = args.foldIndexed(initial = route) { index, fullRoute, arg ->
                val separator = if (index == 0) "/?" else ""
                "$fullRoute$separator$arg={$arg}"
            }
    }
}

private const val NAV_PREFIX: String = "com.msa.petsearch"

private const val SPLASH_DESTINATION: String = "$NAV_PREFIX.splash/SplashScreen"
private const val HOME_DESTINATION: String = "$NAV_PREFIX.home/HomeScreen"
private const val PET_DETAIL_DESTINATION: String = "$NAV_PREFIX.pet_detail/PetDetailScreen"

const val ARG_PET_INFO: String = "arg_pet_info"
