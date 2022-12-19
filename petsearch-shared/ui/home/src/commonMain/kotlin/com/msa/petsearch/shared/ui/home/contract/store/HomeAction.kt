package com.msa.petsearch.shared.ui.home.contract.store

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.core.util.extension.encodeToString
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationScreen
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.NavigationState
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

sealed interface HomeAction : NanoRedux.Action

// _________________________________________________________________________________________________
// ACTIONS WITH SIDE-EFFECT ________________________________________________________________________
object GetInitialData : HomeAction, HomeSideEffect

data class OnPetTypeTabChanged(val tabName: String) : HomeAction, HomeSideEffect

object LoadPetListNextPage : HomeAction, HomeSideEffect

// _________________________________________________________________________________________________
// ACTIONS WITH NAVIGATION _________________________________________________________________________
data class NavigateToPetDetail(val petInfo: PetInfo?) : HomeAction, HomeNavigation {
    override val state = NavigationState.NavigateToRoute(
        route = NavigationScreen.PetDetailNavScreen.route,
        args = hashMapOf(Pair(ARG_PET_INFO, petInfo?.encodeToString() ?: ""))
    )
}

// _________________________________________________________________________________________________
// ACTIONS RETURNED BY SIDE-EFFECT _________________________________________________________________
internal data class ForwardInitialDataToState(
    val petTypes: PetTypesResponse?, val petPagingData: CommonFlow<PagingData<PetInfo>>
) : HomeAction

internal data class ForwardPetResponseToState(val petPagingData: CommonFlow<PagingData<PetInfo>>) :
    HomeAction

internal object IdleAction : HomeAction

internal data class Error(val message: ResourceMessage) : HomeAction
