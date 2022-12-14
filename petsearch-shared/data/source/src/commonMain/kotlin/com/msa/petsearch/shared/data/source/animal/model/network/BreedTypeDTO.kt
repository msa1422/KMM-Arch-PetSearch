package com.msa.petsearch.shared.data.source.animal.model.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypeDTO(
    @SerialName("name") val name: String
)
