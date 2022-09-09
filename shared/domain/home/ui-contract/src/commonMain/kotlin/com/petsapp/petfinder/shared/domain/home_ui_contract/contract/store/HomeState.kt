package com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store

import com.petsapp.petfinder.shared.core_entity.PetSearchParams
import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_entity.response.PaginationInfo
import com.petsapp.petfinder.shared.core_entity.response.PetTypesResponse
import com.petsapp.petfinder.shared.core_util.CommonFlow
import com.petsapp.petfinder.shared.core_util.asCommonFlow
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.flow.flow

data class HomeState(
    val petTypesResponse: PetTypesResponse? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>> = flow<PagingData<PetInfo>> {  }.asCommonFlow(),
    val searchParams: PetSearchParams = PetSearchParams(),


    // TODO: Add implementation
    // Store pagination data here so that each PetType can be resumed from where it was left
    // val paginationMap: HashMap<String, PaginationInfo> = hashMapOf()
) : NanoRedux.State
