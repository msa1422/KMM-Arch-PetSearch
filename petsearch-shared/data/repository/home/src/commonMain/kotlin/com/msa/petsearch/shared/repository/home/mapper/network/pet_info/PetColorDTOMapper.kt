package com.msa.petsearch.shared.repository.home.mapper.network.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetColor
import com.msa.petsearch.shared.networkinfra.dto.pet_info.PetColorDTO

internal fun PetColorDTO.toDomainEntity() =
    PetColor(
        primary = primary ?: "",
        secondary = secondary ?: "",
        tertiary = tertiary ?: ""
    )
