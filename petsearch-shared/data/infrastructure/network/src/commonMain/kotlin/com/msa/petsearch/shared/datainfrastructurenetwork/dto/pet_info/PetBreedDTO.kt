package com.msa.petsearch.shared.datainfrastructurenetwork.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetBreedDTO(
    @SerialName("primary") val primary: String?,
    @SerialName("secondary") val secondary: String?,
    @SerialName("mixed") val mixed: Boolean?,
    @SerialName("unknown") val unknown: Boolean?
)
