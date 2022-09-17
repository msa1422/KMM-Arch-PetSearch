package com.msa.petsearch.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetEnv(
    val isGoodWithChildren: Boolean?,
    val isGoodWithDogs: Boolean?,
    val isGoodWithCats: Boolean?
)