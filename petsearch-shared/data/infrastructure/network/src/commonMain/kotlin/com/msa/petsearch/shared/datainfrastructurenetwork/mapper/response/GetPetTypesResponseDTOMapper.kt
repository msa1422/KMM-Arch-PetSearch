package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.petsapp.petfinder.shared.coreentity.response.PetTypesResponse
import com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.toDomainEntityList
import com.petsapp.petfinder.shared.datainfrastructurenetwork.response.PetTypesResponseDTO

internal fun PetTypesResponseDTO.toDomainEntity() =
    PetTypesResponse(types = types?.toDomainEntityList())
