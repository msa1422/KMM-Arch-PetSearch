package com.msa.petsearch.shared.coreentity.response

import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo

data class SearchPetResponse(
    val animals: List<PetInfo>?,
    val pagination: PaginationInfo?
)
