package com.petsapp.petfinder.shared.core_entity.response

import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo

data class SearchPetResponse(
    val animals: List<PetInfo>?,
    val pagination: PaginationInfo?
)
