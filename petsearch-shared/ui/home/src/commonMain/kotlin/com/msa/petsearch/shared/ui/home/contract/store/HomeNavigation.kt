package com.msa.petsearch.shared.ui.home.contract.store

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.extension.encodeToString
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationState
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

sealed interface HomeNavigation : NanoRedux.Navigation {

    data class NavHomeToPetDetail(val petInfo: PetInfo?) : HomeNavigation {
        override val state: NavigationState =
            NavigationState.NavigateToRoute(
                route = NavigationScreen.PetDetailNavScreen.route,
                args = hashMapOf(Pair(ARG_PET_INFO, petInfo?.encodeToString() ?: ""))
            )
    }
}
