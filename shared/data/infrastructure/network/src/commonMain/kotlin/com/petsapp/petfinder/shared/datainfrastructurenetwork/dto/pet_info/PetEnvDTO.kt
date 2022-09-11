package com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetEnvDTO(
    @SerialName("children") val isGoodWithChildren: Boolean?,
    @SerialName("dogs") val isGoodWithDogs: Boolean?,
    @SerialName("cats") val isGoodWithCats: Boolean?
)