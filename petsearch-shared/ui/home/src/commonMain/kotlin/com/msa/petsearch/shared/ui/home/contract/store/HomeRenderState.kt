package com.msa.petsearch.shared.ui.home.contract.store

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.PetType
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

data class HomeRenderState(
    val petTypes: List<PetType>? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>>
) : NanoRedux.RenderState
