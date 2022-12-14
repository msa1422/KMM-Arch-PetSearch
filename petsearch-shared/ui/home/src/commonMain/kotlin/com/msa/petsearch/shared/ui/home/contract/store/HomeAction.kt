package com.msa.petsearch.shared.ui.home.contract.store

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

sealed interface HomeAction : NanoRedux.Action {

    object GetPetTypes : HomeAction

    data class UpdatePetTypesInState(val petTypesResponse: PetTypesResponse?) : HomeAction

    data class UpdatePetResponseInState(val petPagingData: CommonFlow<PagingData<PetInfo>>) :
        HomeAction

    data class OnPetTypeTabSelected(val tabName: String) : HomeAction

    data class NavigateToPetDetail(val petInfo: PetInfo?) : HomeAction

    object LoadPetListNextPage : HomeAction

    object OnLoadPetListNextPageActionComplete : HomeAction

    data class Error(val message: ResourceMessage) : HomeAction
}
