package com.msa.petsearch.shared.datainfrastructurenetwork.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetInfoDTO(
    @SerialName("id") val id: Long,
    @SerialName("organization_id") val organizationId: String?,
    @SerialName("url") val url: String?,
    @SerialName("type") val type: String?,
    @SerialName("species") val species: String?,
    @SerialName("breeds") val breeds: PetBreedDTO?,
    @SerialName("colors") val colors: PetColorDTO?,
    @SerialName("age") val age: String?,
    @SerialName("gender") val gender: String?,
    @SerialName("size") val size: String?,
    @SerialName("coat") val coat: String?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("photos") val photos: List<PetPhotoDTO>?,
    @SerialName("videos") val videos: List<PetVideoDTO>?,
    @SerialName("status") val status: String?,
    @SerialName("attributes") val attributes: PetAttributesDTO?,
    @SerialName("environment") val environment: PetEnvDTO?,
    @SerialName("tags") val tags: List<String>?,
    @SerialName("contact") val contact: PetContactDTO?,
    @SerialName("published_at") val published_at: String?,
    @SerialName("distance") val distance: Double?
)
