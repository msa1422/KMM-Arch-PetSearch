package com.petsapp.petfinder.shared.coreentity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetContactAddress(
    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
)
