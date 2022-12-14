package com.msa.petsearch.shared.data.source.animal.model.network.response

import com.msa.petsearch.shared.data.source.animal.model.network.PetTypeDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetTypesResponseDTO(
    @SerialName("types") val types: List<PetTypeDTO>?
)
