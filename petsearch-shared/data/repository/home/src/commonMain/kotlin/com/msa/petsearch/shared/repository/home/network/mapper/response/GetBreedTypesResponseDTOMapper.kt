package com.msa.petsearch.shared.repository.home.network.mapper.response

import com.msa.petsearch.shared.coreentity.response.BreedTypesResponse
import com.msa.petsearch.shared.repository.home.network.mapper.toDomainEntityList
import com.msa.petsearch.shared.repository.home.network.response.BreedTypesResponseDTO

internal fun BreedTypesResponseDTO.toDomainEntity() =
    BreedTypesResponse(breeds = breeds?.toDomainEntityList())
