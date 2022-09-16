package com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper

import com.petsapp.petfinder.shared.coreentity.PetType
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.PetTypeDTO

private fun PetTypeDTO.toDomainEntity() =
    PetType(
        name = name,
        coats = coats ?: emptyList(),
        colors = colors ?: emptyList(),
        genders = genders ?: emptyList()
    )

internal fun List<PetTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
