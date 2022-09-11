package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetEnv
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetEnvDTO

internal fun PetEnvDTO.toDomainEntity() =
    PetEnv(
        isGoodWithChildren = isGoodWithChildren,
        isGoodWithDogs = isGoodWithDogs,
        isGoodWithCats = isGoodWithCats
    )
