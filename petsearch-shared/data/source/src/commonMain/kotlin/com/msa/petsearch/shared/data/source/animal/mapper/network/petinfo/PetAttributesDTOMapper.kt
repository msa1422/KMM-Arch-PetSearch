package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetAttributes
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetAttributesDTO

internal fun PetAttributesDTO.toDomainEntity() =
    PetAttributes(
        spayedNeutered = spayedNeutered ?: false,
        houseTrained = houseTrained ?: false,
        declawed = declawed ?: false,
        specialNeeds = specialNeeds ?: false,
        shotsCurrent = shotsCurrent ?: false
    )
