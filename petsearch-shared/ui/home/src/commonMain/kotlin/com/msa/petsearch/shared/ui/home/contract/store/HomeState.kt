package com.msa.petsearch.shared.ui.home.contract.store

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.core.util.commonflow.commonFlowOf
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

data class HomeState(
    val petTypes: PetTypesResponse? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>> = commonFlowOf(),
    val searchParams: PetSearchParams = PetSearchParams()

    // Store pagination data here so that each PetType can be resumed from where it was left
    // val paginationMap: HashMap<String, PaginationInfo> = hashMapOf()
) : NanoRedux.State
