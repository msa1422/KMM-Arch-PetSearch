package com.msa.petsearch.shared.core.entity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetBreed(
    val primary: String,
    val secondary: String,
    val mixed: Boolean,
    val unknown: Boolean
)
