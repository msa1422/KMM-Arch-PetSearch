package com.msa.petsearch.shared.networkinfra.response

import com.msa.petsearch.shared.networkinfra.dto.PetTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetTypesResponseDTO(
    @SerialName("types") val types: List<PetTypeDTO>?
)
