package com.petsapp.petfinder.shared.data_infrastructure_network.mapper

import com.petsapp.petfinder.shared.core_entity.PetType
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.PetTypeDTO

private fun PetTypeDTO.toDomainEntity() =
    PetType(
        name = name,
        coats = coats ?: emptyList(),
        colors = colors ?: emptyList(),
        genders = genders ?: emptyList()
    )

internal fun List<PetTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
