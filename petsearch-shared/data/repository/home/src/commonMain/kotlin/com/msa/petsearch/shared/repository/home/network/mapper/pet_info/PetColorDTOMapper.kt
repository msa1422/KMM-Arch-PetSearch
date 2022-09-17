package com.msa.petsearch.shared.repository.home.network.mapper.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetColor
import com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetColorDTO

internal fun PetColorDTO.toDomainEntity() =
    PetColor(
        primary = primary ?: "",
        secondary = secondary ?: "",
        tertiary = tertiary ?: ""
    )
