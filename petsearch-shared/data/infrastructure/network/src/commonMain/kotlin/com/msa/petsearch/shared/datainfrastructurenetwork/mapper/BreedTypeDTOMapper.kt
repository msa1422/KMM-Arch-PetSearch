package com.msa.petsearch.shared.datainfrastructurenetwork.mapper

import com.petsapp.petfinder.shared.coreentity.BreedType
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.BreedTypeDTO

private fun BreedTypeDTO.toDomainEntity() = BreedType(name = name)

internal fun List<BreedTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
