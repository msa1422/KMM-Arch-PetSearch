package com.petsapp.petfinder.shared.core_entity.pet_info

import kotlinx.serialization.Serializable

@Serializable
data class PetPhoto(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)
