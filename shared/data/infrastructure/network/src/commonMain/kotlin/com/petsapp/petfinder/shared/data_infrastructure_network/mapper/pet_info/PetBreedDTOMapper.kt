package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetBreed
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetBreedDTO

internal fun PetBreedDTO.toDomainEntity() =
    PetBreed(
        primary = primary ?: "",
        secondary = secondary ?: "",
        mixed = mixed ?: false,
        unknown = unknown ?: false
    )
