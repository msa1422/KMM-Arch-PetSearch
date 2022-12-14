package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetColor
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetColorDTO

internal fun PetColorDTO.toDomainEntity() =
    PetColor(
        primary = primary ?: "",
        secondary = secondary ?: "",
        tertiary = tertiary ?: ""
    )
