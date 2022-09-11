package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.response

import com.petsapp.petfinder.shared.core_entity.response.BreedTypesResponse
import com.petsapp.petfinder.shared.data_infrastructure_network.mapper.toDomainEntityList
import com.petsapp.petfinder.shared.data_infrastructure_network.response.BreedTypesResponseDTO

internal fun BreedTypesResponseDTO.toDomainEntity() =
    BreedTypesResponse(breeds = breeds?.toDomainEntityList())
