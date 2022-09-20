package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreutil.extension.encodeToString
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.NavigationState
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux

sealed class HomeNavigation : NanoRedux.Navigation {

    data class NavHomeToPetDetail(val petInfo: PetInfo?) : HomeNavigation() {
        override val state: NavigationState =
            NavigationState.NavigateToRoute(
                route = NavigationScreen.PetDetailNavScreen.route,
                args = hashMapOf(Pair(ARG_PET_INFO, petInfo?.encodeToString() ?: ""))
            )
    }
}
