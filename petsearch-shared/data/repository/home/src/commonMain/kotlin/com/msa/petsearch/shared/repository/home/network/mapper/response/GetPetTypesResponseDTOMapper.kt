package com.msa.petsearch.shared.repository.home.network.mapper.response

import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.repository.home.network.mapper.toDomainEntityList
import com.msa.petsearch.shared.repository.home.network.response.PetTypesResponseDTO

internal fun PetTypesResponseDTO.toDomainEntity() =
    PetTypesResponse(types = types?.toDomainEntityList())
