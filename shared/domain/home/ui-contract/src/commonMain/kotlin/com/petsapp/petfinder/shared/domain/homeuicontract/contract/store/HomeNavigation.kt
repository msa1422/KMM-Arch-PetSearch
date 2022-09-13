package com.petsapp.petfinder.shared.domain.homeuicontract.contract.store

import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo
import com.petsapp.petfinder.shared.coreutil.extension.encodeToString
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.navigation.ARG_PET_INFO
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.navigation.NavigationScreen
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.navigation.NavigationState
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux

sealed class HomeNavigation : NanoRedux.Navigation {

    data class NavHomeToPetDetail(val petInfo: PetInfo?) : HomeNavigation() {
        override val state: NavigationState =
            NavigationState.NavigateToRoute(
                route = NavigationScreen.PetDetailNavScreen.route,
                args = hashMapOf(Pair(ARG_PET_INFO, petInfo?.encodeToString() ?: ""))
            )
    }

}
