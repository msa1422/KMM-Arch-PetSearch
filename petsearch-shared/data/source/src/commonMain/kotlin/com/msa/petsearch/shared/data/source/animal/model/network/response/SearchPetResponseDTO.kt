package com.msa.petsearch.shared.data.source.animal.model.network.response

import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchPetResponseDTO(
    @SerialName("animals") val animals: List<PetInfoDTO>?,
    @SerialName("pagination") val pagination: PaginationInfoDTO?
)
