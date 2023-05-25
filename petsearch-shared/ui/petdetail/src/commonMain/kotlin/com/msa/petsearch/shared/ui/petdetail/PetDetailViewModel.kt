package com.msa.petsearch.shared.ui.petdetail

import com.msa.petsearch.shared.core.util.kmmviewmodel.BaseKmmViewModel
import com.msa.petsearch.shared.core.util.kmmviewmodel.SavedStateHandle
import com.msa.petsearch.shared.ui.petdetail.contract.petInfo
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState

class PetDetailViewModel
internal constructor(savedStateHandle: SavedStateHandle) :
    BaseKmmViewModel<Nothing, Nothing, Nothing>(savedStateHandle) {
    @NativeCoroutinesState
    val petInfo = savedStateHandle.petInfo.stateInWhenSubscribed(initialValue = null)

    // No action to dispatch
    override fun dispatch(action: Nothing) = Unit
}
