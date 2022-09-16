package com.msa.petsearch.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetAttributes(
    val spayedNeutered: Boolean,
    val houseTrained: Boolean,
    val declawed: Boolean,
    val specialNeeds: Boolean,
    val shotsCurrent: Boolean
)
