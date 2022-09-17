package com.msa.petsearch.shared.repository.home.network.response

import com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetDetailResponseDTO(
    @SerialName("animal") val petInfo: PetInfoDTO?
)
