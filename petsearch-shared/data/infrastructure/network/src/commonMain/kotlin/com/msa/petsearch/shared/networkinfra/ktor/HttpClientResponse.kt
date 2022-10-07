package com.msa.petsearch.shared.networkinfra.ktor

internal data class HttpClientResponse<T>(
    val data: T?,
    val error: Throwable?
)

internal fun <T> httpClientResponseFrom(data: T? = null, error: Throwable? = null) =
    HttpClientResponse(data, error)
