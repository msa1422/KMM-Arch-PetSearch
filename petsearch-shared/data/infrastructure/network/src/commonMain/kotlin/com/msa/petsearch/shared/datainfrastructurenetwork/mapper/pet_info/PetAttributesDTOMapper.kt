package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetAttributes
import com.msa.petsearch.shared.datainfrastructurenetwork.dto.pet_info.PetAttributesDTO

internal fun PetAttributesDTO.toDomainEntity() =
    PetAttributes(
        spayedNeutered = spayedNeutered ?: false,
        houseTrained = houseTrained ?: false,
        declawed = declawed ?: false,
        specialNeeds = specialNeeds ?: false,
        shotsCurrent = shotsCurrent ?: false
    )
