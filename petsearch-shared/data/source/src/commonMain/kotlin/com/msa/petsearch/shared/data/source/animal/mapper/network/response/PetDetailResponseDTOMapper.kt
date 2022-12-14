package com.msa.petsearch.shared.data.source.animal.mapper.network.response

import com.msa.petsearch.shared.core.entity.response.PetDetailResponse
import com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo.toDomainEntity
import com.msa.petsearch.shared.data.source.animal.model.network.response.PetDetailResponseDTO

internal fun PetDetailResponseDTO.toDomainEntity(): PetDetailResponse {
    return PetDetailResponse(petInfo = petInfo?.toDomainEntity())
}
