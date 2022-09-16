package com.msa.petsearch.shared.domain.petdetailuicontract.contract.mapper

import com.petsapp.petfinder.shared.coreutil.extension.decodeFromString
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.navigation.ARG_PET_INFO
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.ArgsMapper
import com.petsapp.petfinder.shared.domain.petdetailuicontract.contract.store.PetDetailState

object PetDetailArgsMapper : ArgsMapper<PetDetailState> {
    override fun mapArgsToState(currentState: PetDetailState, args: HashMap<String, String>): PetDetailState {
        return currentState.copy(petInfo = args[ARG_PET_INFO]?.decodeFromString())
    }
}
