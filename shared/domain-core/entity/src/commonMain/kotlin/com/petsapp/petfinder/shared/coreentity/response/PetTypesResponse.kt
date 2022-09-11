package com.petsapp.petfinder.shared.coreentity.response

import com.petsapp.petfinder.shared.coreentity.PetType

data class PetTypesResponse(
    val types: List<PetType>?
)
