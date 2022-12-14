package com.msa.petsearch.shared.ui.petdetail.contract.store

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

data class PetDetailState(val petInfo: PetInfo? = null) : NanoRedux.State
