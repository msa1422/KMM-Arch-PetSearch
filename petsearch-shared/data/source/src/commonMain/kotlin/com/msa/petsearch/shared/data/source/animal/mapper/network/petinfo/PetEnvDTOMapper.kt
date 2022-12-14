package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetEnv
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetEnvDTO

internal fun PetEnvDTO.toDomainEntity() =
    PetEnv(
        isGoodWithChildren = isGoodWithChildren,
        isGoodWithDogs = isGoodWithDogs,
        isGoodWithCats = isGoodWithCats
    )
