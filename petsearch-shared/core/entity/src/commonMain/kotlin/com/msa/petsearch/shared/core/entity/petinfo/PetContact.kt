package com.msa.petsearch.shared.core.entity.petinfo

import kotlinx.serialization.Serializable

@Serializable
data class PetContact(
    val email: String,
    val phone: String,
    val address: PetContactAddress?
)
