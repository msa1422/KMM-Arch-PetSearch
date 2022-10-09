package com.msa.petsearch.shared.networkinfra.testfake

import com.msa.petsearch.shared.networkinfra.EndPoints
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.date.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking

/**
 * A stripped out abstract implementation for KtorMockEngine, adopted from TV-manic
 * https://github.com/c0de-wizard/tv-maniac
 *
 */
@Suppress("unused")
abstract class PetFinderMockEngine {

    private var lastRequest: HttpRequestData? = null
    private var apiUrl: String = ""
    private lateinit var mockResponse: HttpResponseData

    protected val httpClient = HttpClient(MockEngine) {
        install(ContentNegotiation) {
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
                            status = mockResponse.statusCode,
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
