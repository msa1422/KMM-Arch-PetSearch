package com.msa.petsearch.shared.ui.home.contract

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.extension.encodeToString
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationEvent
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract

sealed interface HomeAction : UiContract.Action

// _________________________________________________________________________________________________
// ACTIONS WITH SIDE-EFFECT ________________________________________________________________________
data class OnPetTypeTabChanged(val tabName: String) : HomeAction

object LoadPetListNextPage : HomeAction

// _________________________________________________________________________________________________
// ACTIONS WITH NAVIGATION _________________________________________________________________________
data class NavigateToPetDetail(val petInfo: PetInfo?) : HomeAction, HomeNavigation {
    override val event = NavigationEvent.NavigateToRoute(
        route = NavigationScreen.PetDetailNavScreen.route,
        args = hashMapOf(Pair(ARG_PET_INFO, petInfo?.encodeToString() ?: ""))
    )
}
