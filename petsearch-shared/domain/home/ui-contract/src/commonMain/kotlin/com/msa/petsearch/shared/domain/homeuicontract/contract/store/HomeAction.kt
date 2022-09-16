package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.coreutil.CommonFlow

sealed class HomeAction : NanoRedux.Action {

    object GetPetTypes : HomeAction()

    data class UpdatePetTypesInState(val petTypesResponse: PetTypesResponse?) : HomeAction()

    data class UpdatePetResponseInState(val petPagingData: CommonFlow<PagingData<PetInfo>>) :
        HomeAction()

    data class OnPetTypeTabSelected(val tabName: String) : HomeAction()

    data class NavigateToPetDetail(val petInfo: PetInfo?) : HomeAction()

    object LoadPetListNextPage: HomeAction()

    object OnLoadPetListNextPageActionComplete: HomeAction()

    data class Error(val message: ResourceMessage?): HomeAction()

}
