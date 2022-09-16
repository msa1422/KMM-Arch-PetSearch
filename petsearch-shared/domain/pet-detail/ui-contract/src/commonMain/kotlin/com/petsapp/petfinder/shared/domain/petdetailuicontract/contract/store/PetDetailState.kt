package com.petsapp.petfinder.shared.domain.petdetailuicontract.contract.store

import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux

data class PetDetailState(val petInfo: PetInfo? = null): NanoRedux.State
