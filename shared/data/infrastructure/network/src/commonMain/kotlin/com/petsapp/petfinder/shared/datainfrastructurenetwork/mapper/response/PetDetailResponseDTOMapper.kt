package com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.response

import com.petsapp.petfinder.shared.coreentity.response.PetDetailResponse
import com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.pet_info.toDomainEntity
import com.petsapp.petfinder.shared.datainfrastructurenetwork.response.PetDetailResponseDTO

internal fun PetDetailResponseDTO.toDomainEntity() =
    PetDetailResponse(petInfo = petInfo?.toDomainEntity())
