package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetPhoto
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetPhotoDTO

internal fun PetPhotoDTO.toDomainEntity() =
    PetPhoto(small = small ?: "", medium = medium ?: "", large = large ?: "", full = full ?: "")

internal fun List<PetPhotoDTO>.toDomainEntityList() = map { it.toDomainEntity() }
