package com.petsapp.petfinder.shared.core_entity.pet_info

import kotlinx.serialization.Serializable

@Serializable
data class PetAttributes(
    val spayedNeutered: Boolean,
    val houseTrained: Boolean,
    val declawed: Boolean,
    val specialNeeds: Boolean,
    val shotsCurrent: Boolean
)
