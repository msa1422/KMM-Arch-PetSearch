package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetVideo
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetVideoDTO

internal fun PetVideoDTO.toDomainEntity() = PetVideo(embed = embed ?: "")

internal fun List<PetVideoDTO>.toDomainEntityList() = map(PetVideoDTO::toDomainEntity)
