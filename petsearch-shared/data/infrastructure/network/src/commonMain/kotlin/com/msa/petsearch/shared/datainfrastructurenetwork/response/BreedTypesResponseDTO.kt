package com.msa.petsearch.shared.datainfrastructurenetwork.response

import com.msa.petsearch.shared.datainfrastructurenetwork.dto.BreedTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypesResponseDTO(
    @SerialName("breeds") val breeds: List<BreedTypeDTO>?
)
