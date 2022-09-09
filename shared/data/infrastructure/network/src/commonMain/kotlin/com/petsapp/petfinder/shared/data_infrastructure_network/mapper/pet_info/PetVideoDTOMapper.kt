package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetVideo
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetVideoDTO

internal fun PetVideoDTO.toDomainEntity() = PetVideo(embed = embed ?: "")

internal fun List<PetVideoDTO>.toDomainEntityList() = map { it.toDomainEntity() }