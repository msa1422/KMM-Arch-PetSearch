package com.msa.petsearch.shared.datainfrastructurenetwork.ktor

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

internal expect fun withPlatformEngine(config: HttpClientConfig<*>.() -> Unit): HttpClient
