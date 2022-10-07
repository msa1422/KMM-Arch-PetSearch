package com.msa.petsearch.shared.networkinfra.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetContactDTO(
    @SerialName("email") val email: String?,
    @SerialName("phone") val phone: String?,
    @SerialName("address") val address: PetContactAddressDTO?
)
