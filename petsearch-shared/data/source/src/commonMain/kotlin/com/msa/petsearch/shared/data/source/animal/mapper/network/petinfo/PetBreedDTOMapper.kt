package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetBreed
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetBreedDTO

internal fun PetBreedDTO.toDomainEntity() =
    PetBreed(
        primary = primary ?: "",
        secondary = secondary ?: "",
        mixed = mixed ?: false,
        unknown = unknown ?: false
    )
