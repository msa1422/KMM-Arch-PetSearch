package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.pet_info

import com.petsapp.petfinder.shared.coreentity.petinfo.PetPhoto
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetPhotoDTO

internal fun PetPhotoDTO.toDomainEntity() =
    PetPhoto(small = small ?: "", medium = medium ?: "", large = large ?: "", full = full ?: "")

internal fun List<PetPhotoDTO>.toDomainEntityList() = map { it.toDomainEntity() }
