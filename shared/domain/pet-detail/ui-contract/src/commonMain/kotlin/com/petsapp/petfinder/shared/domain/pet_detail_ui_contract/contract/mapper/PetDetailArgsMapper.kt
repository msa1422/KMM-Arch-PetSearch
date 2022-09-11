package com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.contract.mapper

import com.petsapp.petfinder.shared.core_util.extention.decodeFromString
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.ARG_PET_INFO
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.ArgsMapper
import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.contract.store.PetDetailState

object PetDetailArgsMapper : ArgsMapper<PetDetailState> {
    override fun mapArgsToState(currentState: PetDetailState, args: HashMap<String, String>): PetDetailState {
        return currentState.copy(petInfo = args[ARG_PET_INFO]?.decodeFromString())
    }
}
