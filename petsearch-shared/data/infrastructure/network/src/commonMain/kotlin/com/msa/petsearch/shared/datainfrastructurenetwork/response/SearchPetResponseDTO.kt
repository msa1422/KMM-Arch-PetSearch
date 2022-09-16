package com.msa.petsearch.shared.datainfrastructurenetwork.response

import com.msa.petsearch.shared.datainfrastructurenetwork.dto.pet_info.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchPetResponseDTO(
    @SerialName("animals") val animals: List<PetInfoDTO>?,
    @SerialName("pagination") val pagination: PaginationInfoDTO?
)
