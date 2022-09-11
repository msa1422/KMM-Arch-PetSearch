package com.petsapp.petfinder.shared.domain.homeuicontract.contract.store

import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo
import com.petsapp.petfinder.shared.coreentity.response.PetTypesResponse
import com.petsapp.petfinder.shared.coreutil.resource.ResourceMessage
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData

sealed class HomeAction : NanoRedux.Action {

    object GetPetTypes : HomeAction()

    data class UpdatePetTypesInState(val petTypesResponse: PetTypesResponse?) : HomeAction()

    data class UpdatePetResponseInState(val petPagingData: PagingData<PetInfo>) : HomeAction()

    data class OnPetTypeTabSelected(val tabName: String) : HomeAction()

    data class NavigateToPetDetail(val petInfo: PetInfo?) : HomeAction()

    data class Error(val message: ResourceMessage?): HomeAction()

}
