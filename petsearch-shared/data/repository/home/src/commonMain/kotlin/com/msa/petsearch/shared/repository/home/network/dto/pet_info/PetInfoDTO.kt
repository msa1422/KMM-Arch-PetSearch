package com.msa.petsearch.shared.repository.home.network.dto.pet_info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetInfoDTO(
    @SerialName("id") val id: Long,
    @SerialName("organization_id") val organizationId: String?,
    @SerialName("url") val url: String?,
    @SerialName("type") val type: String?,
    @SerialName("species") val species: String?,
    @SerialName("breeds") val breeds: com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetBreedDTO?,
    @SerialName("colors") val colors: com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetColorDTO?,
    @SerialName("age") val age: String?,
    @SerialName("gender") val gender: String?,
    @SerialName("size") val size: String?,
    @SerialName("coat") val coat: String?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("photos") val photos: List<com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetPhotoDTO>?,
    @SerialName("videos") val videos: List<com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetVideoDTO>?,
    @SerialName("status") val status: String?,
    @SerialName("attributes") val attributes: com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetAttributesDTO?,
    @SerialName("environment") val environment: com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetEnvDTO?,
    @SerialName("tags") val tags: List<String>?,
    @SerialName("contact") val contact: com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetContactDTO?,
    @SerialName("published_at") val published_at: String?,
    @SerialName("distance") val distance: Double?
)
