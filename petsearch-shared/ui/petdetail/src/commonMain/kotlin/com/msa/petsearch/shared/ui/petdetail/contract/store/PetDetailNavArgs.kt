package com.msa.petsearch.shared.ui.petdetail.contract.store

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.sharedviewmodel.contract.UiContract

data class PetDetailNavArgs(val petInfo: PetInfo? = null) : UiContract.NavArgs
