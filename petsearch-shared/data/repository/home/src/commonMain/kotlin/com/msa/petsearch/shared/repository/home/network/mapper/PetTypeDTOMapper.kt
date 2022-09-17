package com.msa.petsearch.shared.repository.home.network.mapper

import com.msa.petsearch.shared.coreentity.PetType
import com.msa.petsearch.shared.repository.home.network.dto.PetTypeDTO

private fun PetTypeDTO.toDomainEntity() =
    PetType(
        name = name,
        coats = coats ?: emptyList(),
        colors = colors ?: emptyList(),
        genders = genders ?: emptyList()
    )

internal fun List<PetTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
