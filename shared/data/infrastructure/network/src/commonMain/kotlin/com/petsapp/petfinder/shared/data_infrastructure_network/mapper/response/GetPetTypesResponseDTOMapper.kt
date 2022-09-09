package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.response

import com.petsapp.petfinder.shared.core_entity.response.PetTypesResponse
import com.petsapp.petfinder.shared.data_infrastructure_network.mapper.toDomainEntityList
import com.petsapp.petfinder.shared.data_infrastructure_network.response.PetTypesResponseDTO

internal fun PetTypesResponseDTO.toDomainEntity() =
    PetTypesResponse(types = types?.toDomainEntityList())