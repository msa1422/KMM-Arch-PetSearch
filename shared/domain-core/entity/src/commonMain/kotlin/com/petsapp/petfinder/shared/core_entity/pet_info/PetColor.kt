package com.petsapp.petfinder.shared.core_entity.pet_info

import kotlinx.serialization.Serializable

@Serializable
data class PetColor(
    val primary: String,
    val secondary: String,
    val tertiary: String
)
