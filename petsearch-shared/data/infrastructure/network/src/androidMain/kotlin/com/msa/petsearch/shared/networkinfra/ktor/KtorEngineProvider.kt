package com.msa.petsearch.shared.networkinfra.ktor

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

internal actual fun withPlatformEngine(
    config: HttpClientConfig<*>.() -> Unit
) = HttpClient(OkHttp) {
    config(this)
    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(30, TimeUnit.SECONDS)
        }
    }
}
