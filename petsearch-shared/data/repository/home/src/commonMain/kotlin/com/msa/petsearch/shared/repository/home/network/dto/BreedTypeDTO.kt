package com.msa.petsearch.shared.repository.home.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypeDTO(
    @SerialName("name") val name: String
)
