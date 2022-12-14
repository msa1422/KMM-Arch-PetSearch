package com.msa.petsearch.shared.data.source.animal.model.network.petinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetContactAddressDTO(
    @SerialName("address1") val address1: String?,
    @SerialName("address2") val address2: String?,
    @SerialName("city") val city: String?,
    @SerialName("state") val state: String?,
    @SerialName("postcode") val postcode: String?,
    @SerialName("country") val country: String?
)
