package com.msa.petsearch.shared.repository.home.mapper.network.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetVideo
import com.msa.petsearch.shared.networkinfra.dto.pet_info.PetVideoDTO

internal fun PetVideoDTO.toDomainEntity() = PetVideo(embed = embed ?: "")

internal fun List<PetVideoDTO>.toDomainEntityList() = map { it.toDomainEntity() }
