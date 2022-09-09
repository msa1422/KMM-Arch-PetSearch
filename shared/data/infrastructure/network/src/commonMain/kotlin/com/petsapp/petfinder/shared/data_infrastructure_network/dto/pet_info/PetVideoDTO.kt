package com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetVideoDTO(
    @SerialName("embed") val embed: String?
)
