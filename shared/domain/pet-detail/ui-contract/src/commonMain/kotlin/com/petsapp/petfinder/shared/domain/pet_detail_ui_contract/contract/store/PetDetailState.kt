package com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.contract.store

import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux

data class PetDetailState(val petInfo: PetInfo? = null): NanoRedux.State
