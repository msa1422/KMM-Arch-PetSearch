package com.msa.petsearch.shared.ui.petdetail.contract.mapper

import com.msa.petsearch.shared.core.util.extension.decodeFromString
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.ArgsMapper
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailNavArgs

internal object PetDetailArgsMapper : ArgsMapper<PetDetailNavArgs> {
    override fun argsFrom(args: HashMap<String, String>) =
        PetDetailNavArgs(petInfo = args[ARG_PET_INFO]?.decodeFromString())
}
