package com.petsapp.petfinder.shared.data_infrastructure_network.response

import com.petsapp.petfinder.shared.data_infrastructure_network.dto.BreedTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BreedTypesResponseDTO(
    @SerialName("breeds") val breeds: List<BreedTypeDTO>?
)
