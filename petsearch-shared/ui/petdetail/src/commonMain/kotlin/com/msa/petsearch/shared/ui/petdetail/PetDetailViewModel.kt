package com.msa.petsearch.shared.ui.petdetail

import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.SavedStateHandle
import com.msa.petsearch.shared.ui.petdetail.contract.petInfo
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState

class PetDetailViewModel
internal constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel<Nothing, Nothing, Nothing>(savedStateHandle) {
    @NativeCoroutinesState
    val petInfo = savedStateHandle.petInfo.stateInWhileSubscribed(initialValue = null)

    // No action to dispatch
    override fun dispatch(action: Nothing) = Unit
}
