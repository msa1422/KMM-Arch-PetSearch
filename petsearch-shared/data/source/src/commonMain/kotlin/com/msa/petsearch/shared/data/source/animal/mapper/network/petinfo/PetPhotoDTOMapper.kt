package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetPhoto
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetPhotoDTO

internal fun PetPhotoDTO.toDomainEntity() =
    PetPhoto(small = small ?: "", medium = medium ?: "", large = large ?: "", full = full ?: "")

internal fun List<PetPhotoDTO>.toDomainEntityList() = map { it.toDomainEntity() }
