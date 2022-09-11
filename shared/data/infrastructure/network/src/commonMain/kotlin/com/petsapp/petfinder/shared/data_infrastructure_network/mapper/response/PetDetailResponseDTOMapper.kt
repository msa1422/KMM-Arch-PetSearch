package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.response

import com.petsapp.petfinder.shared.core_entity.response.PetDetailResponse
import com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info.toDomainEntity
import com.petsapp.petfinder.shared.data_infrastructure_network.response.PetDetailResponseDTO

internal fun PetDetailResponseDTO.toDomainEntity() =
    PetDetailResponse(petInfo = petInfo?.toDomainEntity())
