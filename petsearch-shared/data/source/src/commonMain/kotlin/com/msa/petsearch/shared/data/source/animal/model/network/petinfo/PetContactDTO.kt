package com.msa.petsearch.shared.data.source.animal.model.network.petinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetContactDTO(
    @SerialName("email") val email: String?,
    @SerialName("phone") val phone: String?,
    @SerialName("address") val address: PetContactAddressDTO?
)
