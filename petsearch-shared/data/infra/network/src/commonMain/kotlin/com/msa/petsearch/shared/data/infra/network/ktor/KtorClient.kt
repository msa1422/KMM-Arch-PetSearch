package com.msa.petsearch.shared.data.infra.network.ktor

import com.msa.petsearch.shared.data.infra.network.EndPoints.API_HOST
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val KTOR_REQUEST_TIMEOUT_MILLIS = 30_000L

internal val KtorClient: HttpClient
    get() {
        // Create a list which will hold the Authorization tokens
        // Add a placeholder (Dummy) token for first request(AccessToken request)
        // Since token validity is only 3600 seconds, it is ok to fetch a new token here.
        // In a production app, token should be retrieved from a persistence storage of some kind
        val bearerTokenStorage = mutableListOf(DUMMY_TOKEN)

        val ktorClient = withPlatformEngine {
            Logging {
                logger = Logger.SIMPLE
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains(API_HOST)
                }
            }

            Auth {
                bearer {
                    loadTokens(bearerTokenStorage::last)

                    refreshTokens {
                        bearerTokenStorage.add(
                            provideBearerToken(client) {
                                markAsRefreshTokenRequest()
                            }
                        )

                        bearerTokenStorage.last()
                    }
                }
            }

            httpTimeout {
                requestTimeoutMillis = KTOR_REQUEST_TIMEOUT_MILLIS
                socketTimeoutMillis = KTOR_REQUEST_TIMEOUT_MILLIS
                connectTimeoutMillis = KTOR_REQUEST_TIMEOUT_MILLIS
            }

            contentNegotiation {
                json(
                    Json {
                        explicitNulls = false
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }

            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }

        return ktorClient
    }
