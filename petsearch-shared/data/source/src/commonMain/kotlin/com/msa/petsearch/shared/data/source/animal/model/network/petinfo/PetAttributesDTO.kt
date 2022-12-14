package com.msa.petsearch.shared.data.source.animal.model.network.petinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PetAttributesDTO(
    @SerialName("spayed_neutered") val spayedNeutered: Boolean?,
    @SerialName("house_trained") val houseTrained: Boolean?,
    @SerialName("declawed") val declawed: Boolean?,
    @SerialName("special_needs") val specialNeeds: Boolean?,
    @SerialName("shots_current") val shotsCurrent: Boolean?
)
