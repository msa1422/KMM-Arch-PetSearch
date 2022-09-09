package com.petsapp.petfinder.shared.data_infrastructure_network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetTypeDTO(
    @SerialName("name") val name: String,
    @SerialName("coats") val coats: List<String>?,
    @SerialName("colors") val colors: List<String>?,
    @SerialName("genders") val genders: List<String>?
)
