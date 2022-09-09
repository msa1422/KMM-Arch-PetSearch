package com.petsapp.petfinder.shared.data_infrastructure_network.response

import com.petsapp.petfinder.shared.data_infrastructure_network.dto.PetTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetTypesResponseDTO(
    @SerialName("types") val types: List<PetTypeDTO>?
)
