package com.msa.petsearch.shared.networkinfra

import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.networkinfra.ktor.safeRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Class to encapsulate the network infrastructure components i.e. everything to related to Ktor.
 * More methods like POST, DELETE etc. can be added, if required.
 *
 * @param httpClient Ktor Asynchronous client to perform HTTP requests.
 * Can be replaced with any Network client library without any effect on Repository
 *
 */
class NetworkDelegate
internal constructor(
    @PublishedApi internal val httpClient: HttpClient
) {
    suspend inline fun <reified ResponseType, ReturnType> get(
        host: String,
        path: Array<String>,
        parameters: HashMap<String, Any?>? = null,
        mapper: ResponseType.() -> ReturnType
    ): Resource<ReturnType> {
        val response = httpClient.safeRequest<ResponseType> {
            method = HttpMethod.Get

            url {
                this.protocol = URLProtocol.HTTPS
                this.host = host
                this.path(*path)
            }

            parameters?.forEach { entry ->
                parameter(key = entry.key, value = entry.value)
            }
        }

        return response.data?.asResource(mapper)
            ?: response.error.asResource()
    }
}
