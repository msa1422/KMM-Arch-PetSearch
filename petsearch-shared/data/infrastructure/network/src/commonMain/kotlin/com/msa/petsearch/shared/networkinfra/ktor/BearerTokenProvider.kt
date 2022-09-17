package com.msa.petsearch.shared.networkinfra.ktor

import com.msa.petsearch.shared.networkinfra.BuildKonfig.PETFINDER_API_KEY
import com.msa.petsearch.shared.networkinfra.BuildKonfig.PETFINDER_SECRET
import com.msa.petsearch.shared.networkinfra.dto.TokenInfoDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

// TODO: Find a way to move token provider method to repository,
//  or at least remove Network Infra dependency on Host url and API keys
//  (Abstraction should be last resort)
internal suspend fun provideBearerToken(
    client: HttpClient,
    block: HttpRequestBuilder.() -> Unit = {}
): BearerTokens {
    val tokenInfo: TokenInfoDTO = client.submitForm(
        url = "https://api.petfinder.com/v2/oauth2/token",
        formParameters = Parameters.build {
            append("grant_type", "client_credentials")
            append("client_id", PETFINDER_API_KEY)
            append("client_secret", PETFINDER_SECRET)
        },
        block = block
    ).body()

    return BearerTokens(tokenInfo.accessToken, "")
}

internal val DUMMY_TOKEN
    get() = BearerTokens("", "")
