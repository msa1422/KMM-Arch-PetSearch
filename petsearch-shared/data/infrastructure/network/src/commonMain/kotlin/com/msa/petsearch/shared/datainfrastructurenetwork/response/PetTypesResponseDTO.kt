package com.msa.petsearch.shared.datainfrastructurenetwork.response

import com.msa.petsearch.shared.datainfrastructurenetwork.dto.PetTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetTypesResponseDTO(
    @SerialName("types") val types: List<PetTypeDTO>?
)
