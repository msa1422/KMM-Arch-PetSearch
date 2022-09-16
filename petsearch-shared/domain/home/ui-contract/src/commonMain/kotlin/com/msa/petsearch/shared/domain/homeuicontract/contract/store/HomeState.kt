package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.petsapp.petfinder.shared.coreentity.PetSearchParams
import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo
import com.petsapp.petfinder.shared.coreentity.response.PetTypesResponse
import com.petsapp.petfinder.shared.coreutil.CommonFlow
import com.petsapp.petfinder.shared.coreutil.asCommonFlow
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux
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
