package com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.pet_info

import com.petsapp.petfinder.shared.coreentity.petinfo.PetEnv
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetEnvDTO

internal fun PetEnvDTO.toDomainEntity() =
    PetEnv(
        isGoodWithChildren = isGoodWithChildren,
        isGoodWithDogs = isGoodWithDogs,
        isGoodWithCats = isGoodWithCats
    )
