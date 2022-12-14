package com.msa.petsearch.shared.data.source.animal.mapper.network.response

import com.msa.petsearch.shared.core.entity.response.SearchPetResponse
import com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo.toDomainEntityList
import com.msa.petsearch.shared.data.source.animal.model.network.response.SearchPetResponseDTO

internal fun SearchPetResponseDTO.toDomainEntity() =
    SearchPetResponse(
        animals = animals?.toDomainEntityList(),
        pagination = pagination?.toDomainEntity()
    )
