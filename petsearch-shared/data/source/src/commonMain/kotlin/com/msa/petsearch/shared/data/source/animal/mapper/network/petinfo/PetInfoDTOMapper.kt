package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetAge
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetCoat
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetGender
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetSize
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetStatus
import com.msa.petsearch.shared.core.util.extension.capitalizeWords
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetInfoDTO

internal fun PetInfoDTO.toDomainEntity() =
    PetInfo(
        id = id,
        organizationId = organizationId ?: "",
        url = url ?: "",
        type = type ?: "",
        species = species ?: "",
        breeds = breeds?.toDomainEntity(),
        colors = colors?.toDomainEntity(),
        age = resolvePetAge(age),
        gender = resolvePetGender(gender),
        size = resolvePetSize(size),
        coat = resolvePetCoat(coat),
        name = name?.capitalizeWords() ?: "",
        description = description ?: "",
        shortDescription = formatShortDescription(age, gender, breeds?.primary),
        photos = photos?.toDomainEntityList() ?: emptyList(),
        videos = emptyList(),
        status = resolvePetStatus(status),
        attributes = attributes?.toDomainEntity(),
        environment = environment?.toDomainEntity(),
        tags = tags,
        contact = contact?.toDomainEntity(),
        publishedAt = publishedAt ?: "",
        distance = distance ?: 0.0
    )

internal fun List<PetInfoDTO>.toDomainEntityList() = map { it.toDomainEntity() }

private fun formatShortDescription(age: String?, gender: String?, breed: String?): String {
    var shortDescription = ""

    age?.let {
        shortDescription = shortDescription.plus(it)
    }

    gender?.let {
        shortDescription = shortDescription
            .plus(if (shortDescription.isNotBlank()) " " else "")
            .plus(it)
    }

    shortDescription = shortDescription
        .plus(if (shortDescription.isNotBlank()) "\n" else "")
        .plus(breed ?: "Unspecified breed")

    return shortDescription
}

private fun resolvePetAge(age: String?) =
    age?.uppercase()?.let(PetAge::valueOf) ?: PetAge.UNSPECIFIED

private fun resolvePetGender(gender: String?) =
    gender?.uppercase()?.let(PetGender::valueOf) ?: PetGender.UNKNOWN

private fun resolvePetSize(size: String?) =
    size?.uppercase()?.replace(" ", "_")?.let(PetSize::valueOf) ?: PetSize.UNSPECIFIED

private fun resolvePetCoat(coat: String?) =
    coat?.uppercase()?.let(PetCoat::valueOf) ?: PetCoat.UNSPECIFIED

private fun resolvePetStatus(status: String?) =
    status?.uppercase()?.let(PetStatus::valueOf) ?: PetStatus.UNSPECIFIED
