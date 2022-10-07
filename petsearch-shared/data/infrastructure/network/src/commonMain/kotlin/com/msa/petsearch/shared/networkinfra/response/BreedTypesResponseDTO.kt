package com.msa.petsearch.shared.networkinfra.response

import com.msa.petsearch.shared.networkinfra.dto.BreedTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedTypesResponseDTO(
    @SerialName("breeds") val breeds: List<BreedTypeDTO>?
)
