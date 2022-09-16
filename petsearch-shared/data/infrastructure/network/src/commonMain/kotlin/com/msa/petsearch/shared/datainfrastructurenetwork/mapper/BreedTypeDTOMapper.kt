package com.msa.petsearch.shared.datainfrastructurenetwork.mapper

import com.msa.petsearch.shared.coreentity.BreedType
import com.msa.petsearch.shared.datainfrastructurenetwork.dto.BreedTypeDTO

private fun BreedTypeDTO.toDomainEntity() = BreedType(name = name)

internal fun List<BreedTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
