package com.msa.petsearch.shared.networkinfra.ktor

import com.msa.petsearch.shared.networkinfra.response.NetworkResponse
import com.msa.petsearch.shared.networkinfra.util.ApiErrorException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.utils.io.errors.*
import kotlinx.serialization.SerializationException
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
@PublishedApi
internal suspend inline fun <reified ResponseType> HttpClient.safeRequest(
    builder: HttpRequestBuilder.() -> Unit
): NetworkResponse<ResponseType> {
    return try {
        NetworkResponse.from(request(builder).body())
    } catch (exception: Exception) {
        when (exception) {
            is ApiErrorException,
            is NoTransformationFoundException, is DoubleReceiveException,
            is IllegalArgumentException, is IllegalStateException,
            is ClientRequestException, is ServerResponseException,
            is IOException, is SerializationException -> {
                NetworkResponse.from(error = exception)
            }
            else -> throw exception
        }
    }
}
