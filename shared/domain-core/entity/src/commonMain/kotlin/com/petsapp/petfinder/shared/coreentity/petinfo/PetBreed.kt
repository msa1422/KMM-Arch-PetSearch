package com.petsapp.petfinder.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetBreed(
    val primary: String,
    val secondary: String,
    val mixed: Boolean,
    val unknown: Boolean
)
