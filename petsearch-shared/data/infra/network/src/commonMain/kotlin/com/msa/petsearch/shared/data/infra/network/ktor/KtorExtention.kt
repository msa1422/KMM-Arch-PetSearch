package com.msa.petsearch.shared.data.infra.network.ktor

import com.msa.petsearch.shared.core.util.resource.Resource
import com.msa.petsearch.shared.core.util.resource.asResource
import com.msa.petsearch.shared.data.infra.network.util.ApiErrorException
import io.ktor.client.HttpClient
import io.ktor.client.call.DoubleReceiveException
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

@Suppress("TooGenericExceptionCaught")
suspend inline fun <reified ResponseType, ReturnType> HttpClient.safeRequest(
    mapper: ResponseType.() -> ReturnType,
    builder: HttpRequestBuilder.() -> Unit
): Resource<ReturnType> {
    return try {
        request(builder).body<ResponseType>().asResource(mapper)
    } catch (exception: Exception) {
        when (exception) {
            is ApiErrorException,
            is NoTransformationFoundException, is DoubleReceiveException,
            is IllegalArgumentException, is IllegalStateException,
            is ClientRequestException, is ServerResponseException,
            is IOException, is SerializationException
            -> exception.asResource()

            else -> throw exception
        }
    }
}
