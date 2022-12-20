package com.msa.petsearch.shared.data.source.animal.mapper.network

import com.msa.petsearch.shared.core.entity.PetType
import com.msa.petsearch.shared.data.source.animal.model.network.PetTypeDTO

private fun PetTypeDTO.toDomainEntity() =
    PetType(
        name = name,
        coats = coats ?: emptyList(),
        colors = colors ?: emptyList(),
        genders = genders ?: emptyList()
    )

internal fun List<PetTypeDTO>.toDomainEntityList() = map(PetTypeDTO::toDomainEntity)
