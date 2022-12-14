package com.msa.petsearch.shared.data.source.animal.model.network.response

import com.msa.petsearch.shared.data.source.animal.model.network.BreedTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypesResponseDTO(
    @SerialName("breeds") val breeds: List<BreedTypeDTO>?
)
