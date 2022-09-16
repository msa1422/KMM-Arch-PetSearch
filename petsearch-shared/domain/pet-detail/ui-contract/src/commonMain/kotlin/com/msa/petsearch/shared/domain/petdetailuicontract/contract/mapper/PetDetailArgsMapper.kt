package com.msa.petsearch.shared.domain.petdetailuicontract.contract.mapper

import com.msa.petsearch.shared.coreutil.extension.decodeFromString
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.ArgsMapper
import com.msa.petsearch.shared.domain.petdetailuicontract.contract.store.PetDetailState

object PetDetailArgsMapper : ArgsMapper<PetDetailState> {
    override fun mapArgsToState(currentState: PetDetailState, args: HashMap<String, String>): PetDetailState {
        return currentState.copy(petInfo = args[ARG_PET_INFO]?.decodeFromString())
    }
}
