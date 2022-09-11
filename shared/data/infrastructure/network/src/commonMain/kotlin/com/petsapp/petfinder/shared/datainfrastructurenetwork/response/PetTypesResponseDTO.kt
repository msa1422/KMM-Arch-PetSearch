package com.petsapp.petfinder.shared.datainfrastructurenetwork.response

import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.PetTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetTypesResponseDTO(
    @SerialName("types") val types: List<PetTypeDTO>?
)
