package com.msa.petsearch.shared.repository.home.mapper.network

import com.msa.petsearch.shared.coreentity.BreedType
import com.msa.petsearch.shared.networkinfra.dto.BreedTypeDTO

private fun BreedTypeDTO.toDomainEntity() = BreedType(name = name)

internal fun List<BreedTypeDTO>.toDomainEntityList() = map { it.toDomainEntity() }
