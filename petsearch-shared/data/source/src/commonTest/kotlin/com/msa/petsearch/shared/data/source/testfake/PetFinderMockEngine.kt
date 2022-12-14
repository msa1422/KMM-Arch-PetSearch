package com.msa.petsearch.shared.data.source.testfake

import com.msa.petsearch.shared.data.infra.network.EndPoints
import com.msa.petsearch.shared.data.infra.network.ktor.contentNegotiation
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking

/**
 * A stripped out abstract implementation for KtorMockEngine, adopted from TV-manic
 * [Source](https://github.com/c0de-wizard/tv-maniac)
 */
@Suppress("unused")
open class PetFinderMockEngine {

    private var lastRequest: HttpRequestData? = null
    private var apiUrl: String = ""
    private lateinit var mockResponse: HttpResponseData

    protected val httpClient = HttpClient(MockEngine) {
        contentNegotiation {
            json()
        }

        defaultRequest {
            if (url.host == "localhost") {
                url.protocol = URLProtocol.HTTPS
                url.host = EndPoints.API_HOST
            }
        }

        engine {
            addHandler { request ->
                lastRequest = request
                when (request.url.fullUrl) {
                    apiUrl -> {
                        respond(
                            content = ByteReadChannel(mockResponse.body.toString()),
                            headers = mockResponse.headers,
                            status = mockResponse.statusCode
                        )
                    }
                    else -> error("Unhandled ${request.url.fullUrl}")
                }
            }
        }
    }

    fun enqueueMockResponse(
        endpointSegment: String,
        responseBody: String,
        httpStatusCode: HttpStatusCode = HttpStatusCode.OK
    ) {
        apiUrl = endpointSegment

        runBlocking {
            mockResponse = HttpResponseData(
                statusCode = httpStatusCode,
                headers = headersOf(
                    "Content-Type" to listOf(ContentType.Application.Json.toString())
                ),
                body = responseBody,
                version = HttpProtocolVersion.HTTP_1_1,
                requestTime = GMTDate(),
                callContext = coroutineContext
            )
        }
    }
}

private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"
