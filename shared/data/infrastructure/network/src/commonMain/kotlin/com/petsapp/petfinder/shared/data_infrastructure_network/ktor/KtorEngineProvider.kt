package com.petsapp.petfinder.shared.data_infrastructure_network.ktor

import io.ktor.client.HttpClientConfig
import io.ktor.client.HttpClient

internal expect fun withPlatformEngine(config: HttpClientConfig<*>.() -> Unit): HttpClient