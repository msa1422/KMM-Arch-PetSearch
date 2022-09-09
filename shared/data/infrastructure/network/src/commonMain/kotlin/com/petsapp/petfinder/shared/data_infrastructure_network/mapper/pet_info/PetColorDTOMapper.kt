package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetColor
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetColorDTO

internal fun PetColorDTO.toDomainEntity() =
    PetColor(
        primary = primary ?: "",
        secondary = secondary ?: "",
        tertiary = tertiary ?: ""
    )