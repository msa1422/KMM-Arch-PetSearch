package com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetContactDTO(
    @SerialName("email") val email: String?,
    @SerialName("phone") val phone: String?,
    @SerialName("address") val address: PetContactAddressDTO?
)
