package com.msa.petsearch.shared.ui.petdetail.contract.mapper

import com.msa.petsearch.shared.core.util.extension.decodeFromString
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.ArgsMapper
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailState

internal object PetDetailArgsMapper : ArgsMapper<PetDetailState> {
    override fun mapArgsToState(currentState: PetDetailState, args: HashMap<String, String>) =
        currentState.copy(petInfo = args[ARG_PET_INFO]?.decodeFromString())
}
