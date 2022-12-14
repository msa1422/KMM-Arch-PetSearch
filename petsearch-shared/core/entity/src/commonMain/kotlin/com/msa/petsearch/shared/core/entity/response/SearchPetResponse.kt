package com.msa.petsearch.shared.core.entity.response

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo

data class SearchPetResponse(
    val animals: List<PetInfo>?,
    val pagination: PaginationInfo?
)
