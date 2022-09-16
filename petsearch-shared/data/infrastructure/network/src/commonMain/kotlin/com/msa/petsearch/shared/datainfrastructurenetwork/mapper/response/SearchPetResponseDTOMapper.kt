package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.petsapp.petfinder.shared.coreentity.response.SearchPetResponse
import com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.pet_info.toDomainEntityList
import com.petsapp.petfinder.shared.datainfrastructurenetwork.response.SearchPetResponseDTO

internal fun SearchPetResponseDTO.toDomainEntity() =
    SearchPetResponse(
        animals = animals?.toDomainEntityList(),
        pagination = pagination?.toDomainEntity()
    )
