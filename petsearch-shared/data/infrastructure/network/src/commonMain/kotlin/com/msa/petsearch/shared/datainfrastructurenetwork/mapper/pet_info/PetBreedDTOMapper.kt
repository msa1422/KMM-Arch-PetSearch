package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.pet_info

import com.petsapp.petfinder.shared.coreentity.petinfo.PetBreed
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetBreedDTO

internal fun PetBreedDTO.toDomainEntity() =
    PetBreed(
        primary = primary ?: "",
        secondary = secondary ?: "",
        mixed = mixed ?: false,
        unknown = unknown ?: false
    )
