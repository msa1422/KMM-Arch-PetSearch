package com.msa.petsearch.shared.networkinfra.response

import com.msa.petsearch.shared.networkinfra.dto.pet_info.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPetResponseDTO(
    @SerialName("animals") val animals: List<PetInfoDTO>?,
    @SerialName("pagination") val pagination: PaginationInfoDTO?
)
