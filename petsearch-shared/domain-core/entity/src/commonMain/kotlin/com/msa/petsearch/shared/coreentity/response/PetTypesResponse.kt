package com.msa.petsearch.shared.coreentity.response

import com.petsapp.petfinder.shared.coreentity.PetType

data class PetTypesResponse(
    val types: List<PetType>?
)
