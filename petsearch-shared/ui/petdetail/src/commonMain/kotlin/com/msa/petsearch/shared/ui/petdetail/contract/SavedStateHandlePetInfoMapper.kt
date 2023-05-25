package com.msa.petsearch.shared.ui.petdetail.contract

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.extension.decodeFromString
import com.msa.petsearch.shared.core.util.sharedviewmodel.SavedStateHandle
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Ideally, one should avoid passing a serialized object. Instead only the [PetInfo.id] should be
 * passed and then object should be retrieved from cache or network with the passed `PetInfo.id`.
 */
internal val SavedStateHandle.petInfo: Flow<PetInfo?>
    get() = getStateFlow<String?>(ARG_PET_INFO, null)
        .map { it?.decodeFromString() }
