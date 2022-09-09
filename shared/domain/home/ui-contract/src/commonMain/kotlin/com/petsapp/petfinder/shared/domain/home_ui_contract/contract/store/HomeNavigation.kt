package com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store

import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_util.extention.encodeToString
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.ARG_PET_INFO
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationScreen
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.NavigationState
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux

sealed class HomeNavigation : NanoRedux.Navigation {

    data class NavHomeToPetDetail(val petInfo: PetInfo?) : HomeNavigation() {
        override val state: NavigationState =
            NavigationState.NavigateToRoute(
                route = NavigationScreen.PetDetailNavScreen.route,
                args = hashMapOf(Pair(ARG_PET_INFO, petInfo?.encodeToString() ?: ""))
            )
    }

}
