package com.petsapp.petfinder.shared.core_entity.pet_info

import kotlinx.serialization.Serializable

@Serializable
data class PetBreed(
    val primary: String,
    val secondary: String,
    val mixed: Boolean,
    val unknown: Boolean
)
