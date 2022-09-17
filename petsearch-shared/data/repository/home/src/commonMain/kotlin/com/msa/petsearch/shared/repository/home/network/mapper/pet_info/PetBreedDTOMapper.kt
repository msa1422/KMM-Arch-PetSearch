package com.msa.petsearch.shared.repository.home.network.mapper.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetBreed
import com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetBreedDTO

internal fun PetBreedDTO.toDomainEntity() =
    PetBreed(
        primary = primary ?: "",
        secondary = secondary ?: "",
        mixed = mixed ?: false,
        unknown = unknown ?: false
    )
