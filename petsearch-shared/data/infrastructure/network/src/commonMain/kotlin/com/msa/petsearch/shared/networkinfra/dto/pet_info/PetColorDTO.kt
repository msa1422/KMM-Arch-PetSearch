package com.msa.petsearch.shared.networkinfra.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetColorDTO(
    @SerialName("primary") val primary: String?,
    @SerialName("secondary") val secondary: String?,
    @SerialName("tertiary") val tertiary: String?
)
