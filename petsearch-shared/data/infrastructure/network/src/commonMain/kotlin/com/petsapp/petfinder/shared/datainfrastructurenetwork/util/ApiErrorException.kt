package com.petsapp.petfinder.shared.datainfrastructurenetwork.util

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiErrorException(
    @SerialName("type") val type: String,
    @SerialName("status") val status: Int,
    @SerialName("title") val title: String,
    @SerialName("detail") val detail: String,
    @SerialName("invalid-params") val invalidParams: List<InvalidParams>
) : RuntimeException()

@Serializable
internal data class InvalidParams(
    @SerialName("in") val place: String,
    @SerialName("path") val path: String,
    @SerialName("message") val message: String
)
