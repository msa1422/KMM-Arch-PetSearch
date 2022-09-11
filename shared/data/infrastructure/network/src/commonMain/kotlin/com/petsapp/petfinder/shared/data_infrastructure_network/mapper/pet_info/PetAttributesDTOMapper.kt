package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetAttributes
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetAttributesDTO

internal fun PetAttributesDTO.toDomainEntity() =
    PetAttributes(
        spayedNeutered = spayedNeutered ?: false,
        houseTrained = houseTrained ?: false,
        declawed = declawed ?: false,
        specialNeeds = specialNeeds ?: false,
        shotsCurrent = shotsCurrent ?: false
    )
