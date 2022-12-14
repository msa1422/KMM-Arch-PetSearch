package com.msa.petsearch.shared.data.infra.network.ktor

import com.msa.petsearch.shared.data.infra.network.BuildKonfig.PETFINDER_API_KEY
import com.msa.petsearch.shared.data.infra.network.BuildKonfig.PETFINDER_SECRET
import com.msa.petsearch.shared.data.infra.network.EndPoints.ACCESS_TOKEN
import com.msa.petsearch.shared.data.infra.network.EndPoints.API_HOST
import com.msa.petsearch.shared.data.infra.network.util.TokenInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.submitForm
import io.ktor.http.Parameters

internal suspend fun provideBearerToken(
    client: HttpClient,
    block: HttpRequestBuilder.() -> Unit = {}
): BearerTokens {
    val tokenInfo: TokenInfo = client.submitForm(
        url = "https://$API_HOST/$ACCESS_TOKEN",
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
