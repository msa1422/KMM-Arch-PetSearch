package com.msa.petsearch.shared.networkinfra.ktor

import com.msa.petsearch.shared.networkinfra.EndPoints.API_HOST
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal val KtorClient: HttpClient
    get() {
        // Create a list which will hold the Authorization tokens
        // Add a placeholder (Dummy) token for first request(AccessToken request)
        // Since token validity is only 3600 seconds, it is ok to fetch a new token here.
        // In a production app, token should be retrieved from a persistence storage of some kind
        val bearerTokenStorage = mutableListOf(DUMMY_TOKEN)

        val ktorClient = withPlatformEngine {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains(API_HOST)
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        bearerTokenStorage.last()
                    }

                    refreshTokens {
                        val refreshToken = provideBearerToken(client) {
                            markAsRefreshTokenRequest()
                        }

                        bearerTokenStorage.add(refreshToken)

                        return@refreshTokens bearerTokenStorage.last()
                    }
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                socketTimeoutMillis = 30000
                connectTimeoutMillis = 30000
            }

            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }

        return ktorClient
    }
