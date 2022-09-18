package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.msa.petsearch.shared.coreentity.PetType
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreutil.commonflow.CommonFlow
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData

data class HomeRenderState(
    val petTypes: List<PetType>? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>>
) : NanoRedux.RenderState
