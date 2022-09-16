package com.petsapp.petfinder.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetContact(
    val email: String,
    val phone: String,
    val address: PetContactAddress?
)
