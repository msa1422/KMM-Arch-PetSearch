package com.msa.petsearch.shared.repository.home.network.mapper.response

import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.repository.home.network.mapper.pet_info.toDomainEntityList
import com.msa.petsearch.shared.repository.home.network.response.SearchPetResponseDTO

internal fun SearchPetResponseDTO.toDomainEntity() =
    SearchPetResponse(
        animals = animals?.toDomainEntityList(),
        pagination = pagination?.toDomainEntity()
    )
