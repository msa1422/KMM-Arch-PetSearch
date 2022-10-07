package com.msa.petsearch.shared.repository.home.mapper.network.response

import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.repository.home.mapper.network.pet_info.toDomainEntityList
import com.msa.petsearch.shared.networkinfra.response.SearchPetResponseDTO

internal fun SearchPetResponseDTO.toDomainEntity() =
    SearchPetResponse(
        animals = animals?.toDomainEntityList(),
        pagination = pagination?.toDomainEntity()
    )
