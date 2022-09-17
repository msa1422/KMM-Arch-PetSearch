package com.msa.petsearch.shared.repository.home.network.mapper.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetVideo
import com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetVideoDTO

internal fun PetVideoDTO.toDomainEntity() = PetVideo(embed = embed ?: "")

internal fun List<PetVideoDTO>.toDomainEntityList() = map { it.toDomainEntity() }
