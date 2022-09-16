package com.msa.petsearch.shared.datainfrastructurenetwork.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetColorDTO(
    @SerialName("primary") val primary: String?,
    @SerialName("secondary") val secondary: String?,
    @SerialName("tertiary") val tertiary: String?
)
