package com.msa.petsearch.shared.ui.petdetail

import com.msa.petsearch.shared.core.util.extension.stateInWhenSubscribed
import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.ui.petdetail.contract.mapper.PetDetailArgsMapper
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailNavArgs
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.map

class PetDetailViewModel
internal constructor() : BaseViewModel<Nothing, Nothing, Nothing, PetDetailNavArgs>(
    emptyArgs = PetDetailNavArgs(),
    argsMapper = PetDetailArgsMapper
) {
    @NativeCoroutines
    val petInfo = navArgs
        .map { it.petInfo }
        .stateInWhenSubscribed(scope = viewModelScope, initialValue = null)

    // No action to dispatch
    override fun dispatch(action: Nothing) = Unit
}
