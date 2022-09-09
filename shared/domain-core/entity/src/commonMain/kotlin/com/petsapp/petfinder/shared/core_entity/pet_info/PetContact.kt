package com.petsapp.petfinder.shared.core_entity.pet_info

import kotlinx.serialization.Serializable

@Serializable
data class PetContact(
    val email: String,
    val phone: String,
    val address: PetContactAddress?
)
