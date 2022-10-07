package com.msa.petsearch.shared.repository.home.mapper.network.response

import com.msa.petsearch.shared.coreentity.response.PetDetailResponse
import com.msa.petsearch.shared.repository.home.mapper.network.pet_info.toDomainEntity
import com.msa.petsearch.shared.networkinfra.response.PetDetailResponseDTO

internal fun PetDetailResponseDTO.toDomainEntity(): PetDetailResponse {
    return PetDetailResponse(petInfo = petInfo?.toDomainEntity())
}
