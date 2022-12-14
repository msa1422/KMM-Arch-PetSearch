package com.msa.petsearch.shared.core.entity.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.enum.PetAge
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetCoat
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetGender
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetSize
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetStatus
import kotlinx.serialization.Serializable

@Serializable
data class PetInfo(
    val id: Long,
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: PetBreed?,
    val colors: PetColor?,
    val age: PetAge,
    val gender: PetGender,
    val size: PetSize,
    val coat: PetCoat,
    val name: String,
    val description: String,
    val shortDescription: String,
    val photos: List<PetPhoto>,
    val videos: List<PetVideo>,
    val status: PetStatus,
    val attributes: PetAttributes?,
    val environment: PetEnv?,
    val tags: List<String>?,
    val contact: PetContact?,
    val publishedAt: String,
    val distance: Double
)
