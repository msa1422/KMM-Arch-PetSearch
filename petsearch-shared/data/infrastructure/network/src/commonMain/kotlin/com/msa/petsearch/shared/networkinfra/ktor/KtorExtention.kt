package com.msa.petsearch.shared.networkinfra.ktor

import com.msa.petsearch.shared.networkinfra.util.ApiErrorException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.SerializationException

internal suspend inline fun <reified ResponseType> HttpClient.safeRequest(
    builder: HttpRequestBuilder.() -> Unit
): HttpClientResponse<ResponseType> {
    return try {
        httpClientResponseFrom(data = request(builder).body())
    } catch (exception: Exception) {
        when (exception) {
            is ApiErrorException,
            is NoTransformationFoundException, is DoubleReceiveException,
            is IllegalArgumentException, is IllegalStateException,
            is ClientRequestException, is ServerResponseException,
            is IOException, is SerializationException -> {
                httpClientResponseFrom(error = exception)
            }
            else -> throw exception
        }
    }
}
