package com.msa.petsearch.shared.repository.home.mapper.network.response

import com.msa.petsearch.shared.coreentity.response.BreedTypesResponse
import com.msa.petsearch.shared.repository.home.mapper.network.toDomainEntityList
import com.msa.petsearch.shared.networkinfra.response.BreedTypesResponseDTO

internal fun BreedTypesResponseDTO.toDomainEntity() =
    BreedTypesResponse(breeds = breeds?.toDomainEntityList())
