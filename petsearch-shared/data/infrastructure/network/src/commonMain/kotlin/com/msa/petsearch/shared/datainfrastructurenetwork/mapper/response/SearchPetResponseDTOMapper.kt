package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.datainfrastructurenetwork.mapper.pet_info.toDomainEntityList
import com.msa.petsearch.shared.datainfrastructurenetwork.response.SearchPetResponseDTO

internal fun SearchPetResponseDTO.toDomainEntity() =
    SearchPetResponse(
        animals = animals?.toDomainEntityList(),
        pagination = pagination?.toDomainEntity()
    )
