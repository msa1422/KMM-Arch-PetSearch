package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.petsapp.petfinder.shared.coreentity.PetType
import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo
import com.petsapp.petfinder.shared.coreutil.CommonFlow
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData

data class HomeRenderState(
    val petTypes: List<PetType>? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>>
) : NanoRedux.RenderState
