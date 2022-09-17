package com.msa.petsearch.shared.repository.home.network.response

import com.msa.petsearch.shared.repository.home.network.dto.PetTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetTypesResponseDTO(
    @SerialName("types") val types: List<PetTypeDTO>?
)
