package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.coreutil.commonflow.CommonFlow
import com.msa.petsearch.shared.coreutil.commonflow.asCommonFlow
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val petTypesResponse: PetTypesResponse? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>> = emptyFlow<PagingData<PetInfo>>().asCommonFlow(),
    val searchParams: PetSearchParams = PetSearchParams(),


    // TODO: Add implementation
    // Store pagination data here so that each PetType can be resumed from where it was left
    // val paginationMap: HashMap<String, PaginationInfo> = hashMapOf()
) : NanoRedux.State
