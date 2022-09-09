package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.response

import com.petsapp.petfinder.shared.core_entity.response.SearchPetResponse
import com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info.toDomainEntityList
import com.petsapp.petfinder.shared.data_infrastructure_network.response.SearchPetResponseDTO

internal fun SearchPetResponseDTO.toDomainEntity() =
    SearchPetResponse(
        animals = animals?.toDomainEntityList(),
        pagination = pagination?.toDomainEntity()
    )