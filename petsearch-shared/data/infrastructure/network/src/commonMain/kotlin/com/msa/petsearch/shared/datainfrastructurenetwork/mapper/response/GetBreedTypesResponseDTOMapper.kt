package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.msa.petsearch.shared.coreentity.response.BreedTypesResponse
import com.msa.petsearch.shared.datainfrastructurenetwork.mapper.toDomainEntityList
import com.msa.petsearch.shared.datainfrastructurenetwork.response.BreedTypesResponseDTO

internal fun BreedTypesResponseDTO.toDomainEntity() =
    BreedTypesResponse(breeds = breeds?.toDomainEntityList())
