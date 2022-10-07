package com.msa.petsearch.shared.networkinfra.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetEnvDTO(
    @SerialName("children") val isGoodWithChildren: Boolean?,
    @SerialName("dogs") val isGoodWithDogs: Boolean?,
    @SerialName("cats") val isGoodWithCats: Boolean?
)
