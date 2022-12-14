package com.msa.petsearch.shared.core.entity.response

import com.msa.petsearch.shared.core.entity.PetType

data class PetTypesResponse(
    val types: List<PetType>?
)
