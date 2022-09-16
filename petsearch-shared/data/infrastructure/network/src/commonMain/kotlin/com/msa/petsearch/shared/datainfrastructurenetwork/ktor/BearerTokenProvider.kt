package com.msa.petsearch.shared.datainfrastructurenetwork.ktor

import com.msa.petsearch.shared.datainfrastructurenetwork.BuildKonfig
import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.EndPoints.ACCESS_TOKEN
import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.EndPoints.API_HOST
import com.msa.petsearch.shared.datainfrastructurenetwork.dto.TokenInfoDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

internal suspend fun provideBearerToken(
    client: HttpClient,
    block: HttpRequestBuilder.() -> Unit = {}
): BearerTokens {
    val tokenInfo: TokenInfoDTO = client.submitForm(
        url = "https://$API_HOST/$ACCESS_TOKEN", // TODO: Replace with a URLBuilder method
        formParameters = Parameters.build {
            append("grant_type", "client_credentials")
            append("client_id", BuildKonfig.PETFINDER_API_KEY)
            append("client_secret", BuildKonfig.PETFINDER_SECRET)
        },
        block = block
    ).body()

    return BearerTokens(tokenInfo.accessToken, "")
}

internal val DUMMY_TOKEN
    get() = BearerTokens("", "")
