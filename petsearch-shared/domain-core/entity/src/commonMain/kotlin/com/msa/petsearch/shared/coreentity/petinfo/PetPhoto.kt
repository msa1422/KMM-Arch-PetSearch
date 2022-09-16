package com.msa.petsearch.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetPhoto(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)
