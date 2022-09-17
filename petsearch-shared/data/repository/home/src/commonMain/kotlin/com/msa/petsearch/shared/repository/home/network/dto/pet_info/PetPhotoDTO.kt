package com.msa.petsearch.shared.repository.home.network.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetPhotoDTO(
    @SerialName("small") val small: String?,
    @SerialName("medium") val medium: String?,
    @SerialName("large") val large: String?,
    @SerialName("full") val full: String?
)
