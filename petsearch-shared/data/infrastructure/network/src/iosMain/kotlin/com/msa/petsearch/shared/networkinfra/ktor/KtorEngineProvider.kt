package com.msa.petsearch.shared.networkinfra.ktor

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

internal actual fun withPlatformEngine(
    config: HttpClientConfig<*>.() -> Unit
) = HttpClient(Darwin) {
    config(this)
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}
