package com.msa.petsearch.shared.repository.home.network.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetVideoDTO(
    @SerialName("embed") val embed: String?
)
