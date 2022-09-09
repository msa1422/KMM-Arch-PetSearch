package com.petsapp.petfinder.shared.core_entity.response

import com.petsapp.petfinder.shared.core_entity.PetType

data class PetTypesResponse(
    val types: List<PetType>?
)
