package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetInfo
import com.petsapp.petfinder.shared.core_entity.pet_info.enum.*
import com.petsapp.petfinder.shared.core_util.extention.capitalizeWords
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetInfoDTO

internal fun PetInfoDTO.toDomainEntity() =
    PetInfo(
        id = id,
        organizationId = organizationId ?: "",
        url = url ?: "",
        type = type ?: "",
        species = species ?: "",
        breeds = breeds?.toDomainEntity(),
        colors = colors?.toDomainEntity(),
        age = age?.uppercase()?.let { PetAge.valueOf(it) } ?: PetAge.UNSPECIFIED,
        gender = gender?.uppercase()?.let { PetGender.valueOf(it) } ?: PetGender.UNKNOWN,
        size = size?.uppercase()?.replace(" ", "_")?.let { PetSize.valueOf(it) } ?: PetSize.UNSPECIFIED,
        coat = coat?.uppercase()?.let { PetCoat.valueOf(it) } ?: PetCoat.UNSPECIFIED,
        name = name?.capitalizeWords() ?: "",
        description = description ?: "",
        shortDescription = formatShortDescription(age, gender, breeds?.primary),
        photos = photos?.toDomainEntityList() ?: emptyList(),
        videos = videos?.toDomainEntityList() ?: emptyList(),
        status = status?.uppercase()?.let { PetStatus.valueOf(it) } ?: PetStatus.UNSPECIFIED,
        attributes = attributes?.toDomainEntity(),
        environment = environment?.toDomainEntity(),
        tags = tags,
        contact = contact?.toDomainEntity(),
        published_at = published_at ?: "",
        distance = distance ?: 0.0
    )


private fun formatShortDescription(age: String?, gender: String?, breed: String?) : String {

    var shortDescription = ""

    age?.let {
        shortDescription = shortDescription.plus(it)
    }

    gender?.let {
        shortDescription = shortDescription
            .plus( if(shortDescription.isNotBlank()) " " else "")
            .plus(it)
    }

    shortDescription = shortDescription
        .plus( if(shortDescription.isNotBlank()) "\n" else "")
        .plus(breed ?: "Unspecified breed")

    return shortDescription
}



internal fun List<PetInfoDTO>.toDomainEntityList() = map { it.toDomainEntity() }