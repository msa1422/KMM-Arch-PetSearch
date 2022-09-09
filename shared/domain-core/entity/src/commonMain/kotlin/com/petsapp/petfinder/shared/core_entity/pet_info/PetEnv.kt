package com.petsapp.petfinder.shared.core_entity.pet_info

import kotlinx.serialization.Serializable

@Serializable
data class PetEnv(
    val isGoodWithChildren: Boolean?,
    val isGoodWithDogs: Boolean?,
    val isGoodWithCats: Boolean?
)
