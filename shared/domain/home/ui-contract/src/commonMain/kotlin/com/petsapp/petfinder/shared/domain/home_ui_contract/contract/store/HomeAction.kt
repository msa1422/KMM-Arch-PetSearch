package com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store

import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_entity.response.PetTypesResponse
import com.petsapp.petfinder.shared.core_entity.response.SearchPetResponse
import com.petsapp.petfinder.shared.core_util.resource.ResourceMessage
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.PagingResult

sealed class HomeAction : NanoRedux.Action {

    object GetPetTypes : HomeAction()

    data class UpdatePetTypesInState(val petTypesResponse: PetTypesResponse?) : HomeAction()

    data class UpdatePetResponseInState(val petPagingData: PagingData<PetInfo>) : HomeAction()

    data class OnPetTypeTabSelected(val tabName: String) : HomeAction()

    data class NavigateToPetDetail(val petInfo: PetInfo?) : HomeAction()

    data class Error(val message: ResourceMessage?): HomeAction()

}
