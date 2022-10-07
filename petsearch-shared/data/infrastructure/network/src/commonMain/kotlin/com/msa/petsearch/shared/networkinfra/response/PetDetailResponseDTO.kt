package com.msa.petsearch.shared.networkinfra.response

import com.msa.petsearch.shared.networkinfra.dto.pet_info.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetDetailResponseDTO(
    @SerialName("animal") val petInfo: PetInfoDTO?
)
