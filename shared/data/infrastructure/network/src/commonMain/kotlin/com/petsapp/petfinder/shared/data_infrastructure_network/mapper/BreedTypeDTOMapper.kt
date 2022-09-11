package com.petsapp.petfinder.shared.data_infrastructure_network.mapper

import com.petsapp.petfinder.shared.core_entity.BreedType
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.BreedTypeDTO

private fun BreedTypeDTO.toDomainEntity() = BreedType(name = name)

internal fun List<BreedTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
