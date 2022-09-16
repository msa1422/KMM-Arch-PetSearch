package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.petsapp.petfinder.shared.coreentity.response.BreedTypesResponse
import com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.toDomainEntityList
import com.petsapp.petfinder.shared.datainfrastructurenetwork.response.BreedTypesResponseDTO

internal fun BreedTypesResponseDTO.toDomainEntity() =
    BreedTypesResponse(breeds = breeds?.toDomainEntityList())
