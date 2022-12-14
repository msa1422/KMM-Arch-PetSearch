package com.msa.petsearch.shared.data.source.animal.mapper.network.response

import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.data.source.animal.mapper.network.toDomainEntityList
import com.msa.petsearch.shared.data.source.animal.model.network.response.PetTypesResponseDTO

internal fun PetTypesResponseDTO.toDomainEntity() =
    PetTypesResponse(types = types?.toDomainEntityList())
