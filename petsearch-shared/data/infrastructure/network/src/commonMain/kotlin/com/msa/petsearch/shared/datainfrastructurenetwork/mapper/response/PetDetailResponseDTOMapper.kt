package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.msa.petsearch.shared.coreentity.response.PetDetailResponse
import com.msa.petsearch.shared.datainfrastructurenetwork.mapper.pet_info.toDomainEntity
import com.msa.petsearch.shared.datainfrastructurenetwork.response.PetDetailResponseDTO

internal fun PetDetailResponseDTO.toDomainEntity() =
    PetDetailResponse(petInfo = petInfo?.toDomainEntity())
