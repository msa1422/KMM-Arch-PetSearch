package com.msa.petsearch.shared.datainfrastructurenetwork.response

import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetInfoDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetDetailResponseDTO(
    @SerialName("animal") val petInfo: PetInfoDTO?
)
