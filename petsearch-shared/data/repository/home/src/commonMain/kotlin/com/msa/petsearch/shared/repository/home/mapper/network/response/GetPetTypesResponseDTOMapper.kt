package com.msa.petsearch.shared.repository.home.mapper.network.response

import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.repository.home.mapper.network.toDomainEntityList
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO

internal fun PetTypesResponseDTO.toDomainEntity() =
    PetTypesResponse(types = types?.toDomainEntityList())
