package com.msa.petsearch.shared.repository.home.network.response

import com.msa.petsearch.shared.repository.home.network.dto.BreedTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypesResponseDTO(
    @SerialName("breeds") val breeds: List<BreedTypeDTO>?
)
