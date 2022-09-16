package com.msa.petsearch.shared.coreentity.petinfo

import com.petsapp.petfinder.shared.coreentity.petinfo.enum.*
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
    val published_at: String,
    val distance: Double
)
