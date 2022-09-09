package com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store

import com.petsapp.petfinder.shared.core_entity.PetType
import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_util.CommonFlow
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux
import com.kuuurt.paging.multiplatform.PagingData

data class HomeRenderState(
    val petTypes: List<PetType>? = null,
    val petPagingData: CommonFlow<PagingData<PetInfo>>
) : NanoRedux.RenderState
