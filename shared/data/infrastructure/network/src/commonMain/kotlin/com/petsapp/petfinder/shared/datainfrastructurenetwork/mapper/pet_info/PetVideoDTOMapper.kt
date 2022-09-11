package com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.pet_info

import com.petsapp.petfinder.shared.coreentity.petinfo.PetVideo
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetVideoDTO

internal fun PetVideoDTO.toDomainEntity() = PetVideo(embed = embed ?: "")

internal fun List<PetVideoDTO>.toDomainEntityList() = map { it.toDomainEntity() }
