package com.msa.petsearch.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetColor(
    val primary: String,
    val secondary: String,
    val tertiary: String
)
