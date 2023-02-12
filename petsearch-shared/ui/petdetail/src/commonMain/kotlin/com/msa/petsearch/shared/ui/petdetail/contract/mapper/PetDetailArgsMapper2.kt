package com.msa.petsearch.shared.ui.petdetail.contract.mapper

import com.msa.petsearch.shared.core.util.extension.decodeFromString
import com.msa.petsearch.shared.core.util.sharedviewmodel.ArgsMapper2
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailState

internal object PetDetailArgsMapper2 : ArgsMapper2<PetDetailState> {
    override fun stateFrom(args: HashMap<String, String>) =
        PetDetailState(petInfo = args[ARG_PET_INFO]?.decodeFromString())
}
