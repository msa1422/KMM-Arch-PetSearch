package com.msa.petsearch.shared.domain.petdetailuicontract.contract.store

import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux

data class PetDetailState(val petInfo: PetInfo? = null): NanoRedux.State
