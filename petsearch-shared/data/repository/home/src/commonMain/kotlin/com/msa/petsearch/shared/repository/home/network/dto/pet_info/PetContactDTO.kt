package com.msa.petsearch.shared.repository.home.network.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetContactDTO(
    @SerialName("email") val email: String?,
    @SerialName("phone") val phone: String?,
    @SerialName("address") val address: com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetContactAddressDTO?
)
