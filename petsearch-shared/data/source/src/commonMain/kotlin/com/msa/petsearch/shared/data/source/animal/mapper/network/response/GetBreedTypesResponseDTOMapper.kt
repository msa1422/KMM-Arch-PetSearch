package com.msa.petsearch.shared.data.source.animal.mapper.network.response

import com.msa.petsearch.shared.core.entity.response.BreedTypesResponse
import com.msa.petsearch.shared.data.source.animal.mapper.network.toDomainEntityList
import com.msa.petsearch.shared.data.source.animal.model.network.response.BreedTypesResponseDTO

internal fun BreedTypesResponseDTO.toDomainEntity() =
    BreedTypesResponse(breeds = breeds?.toDomainEntityList())
