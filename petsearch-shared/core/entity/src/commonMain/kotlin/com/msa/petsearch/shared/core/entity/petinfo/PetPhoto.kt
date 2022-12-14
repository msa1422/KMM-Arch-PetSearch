package com.msa.petsearch.shared.core.entity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetPhoto(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)
