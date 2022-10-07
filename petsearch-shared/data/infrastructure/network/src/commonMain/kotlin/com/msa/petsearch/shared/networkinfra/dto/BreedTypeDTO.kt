package com.msa.petsearch.shared.networkinfra.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedTypeDTO(
    @SerialName("name") val name: String
)
