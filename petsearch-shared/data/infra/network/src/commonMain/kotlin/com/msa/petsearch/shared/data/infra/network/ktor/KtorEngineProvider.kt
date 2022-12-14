package com.msa.petsearch.shared.data.infra.network.ktor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

internal expect fun withPlatformEngine(config: HttpClientConfig<*>.() -> Unit): HttpClient
