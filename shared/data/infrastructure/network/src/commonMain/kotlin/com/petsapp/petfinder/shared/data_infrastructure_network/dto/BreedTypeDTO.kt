package com.petsapp.petfinder.shared.data_infrastructure_network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypeDTO(
    @SerialName("name") val name: String
)
