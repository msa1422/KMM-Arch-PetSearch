package com.petsapp.petfinder.shared.data_infrastructure_network.response

import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchPetResponseDTO(
    @SerialName("animals") val animals: List<PetInfoDTO>?,
    @SerialName("pagination") val pagination: PaginationInfoDTO?
)
