package com.msa.petsearch.shared.repository.home.network.mapper.response

import com.msa.petsearch.shared.coreentity.response.PetDetailResponse
import com.msa.petsearch.shared.repository.home.network.mapper.pet_info.toDomainEntity
import com.msa.petsearch.shared.repository.home.network.response.PetDetailResponseDTO

internal fun PetDetailResponseDTO.toDomainEntity(): PetDetailResponse {
    return PetDetailResponse(petInfo = petInfo?.toDomainEntity())
}
