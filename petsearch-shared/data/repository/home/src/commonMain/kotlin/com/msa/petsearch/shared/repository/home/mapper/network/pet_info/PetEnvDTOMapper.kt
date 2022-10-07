package com.msa.petsearch.shared.repository.home.mapper.network.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetEnv
import com.msa.petsearch.shared.networkinfra.dto.pet_info.PetEnvDTO

internal fun PetEnvDTO.toDomainEntity() =
    PetEnv(
        isGoodWithChildren = isGoodWithChildren,
        isGoodWithDogs = isGoodWithDogs,
        isGoodWithCats = isGoodWithCats
    )
