package com.msa.petsearch.shared.data.source.animal.mapper.network

import com.msa.petsearch.shared.core.entity.BreedType
import com.msa.petsearch.shared.data.source.animal.model.network.BreedTypeDTO

private fun BreedTypeDTO.toDomainEntity() = BreedType(name = name)

internal fun List<BreedTypeDTO>.toDomainEntityList() = map(BreedTypeDTO::toDomainEntity)
