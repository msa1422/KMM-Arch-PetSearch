package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.datainfrastructurenetwork.mapper.toDomainEntityList
import com.msa.petsearch.shared.datainfrastructurenetwork.response.PetTypesResponseDTO

internal fun PetTypesResponseDTO.toDomainEntity() =
    PetTypesResponse(types = types?.toDomainEntityList())
