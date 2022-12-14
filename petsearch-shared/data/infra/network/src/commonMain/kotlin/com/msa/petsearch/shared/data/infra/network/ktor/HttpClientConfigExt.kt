@file:Suppress("FunctionName")

package com.msa.petsearch.shared.data.infra.network.ktor

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpTimeout.HttpTimeoutCapabilityConfiguration
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

/**
 * Install [httpTimeout] plugin.
 */
internal fun HttpClientConfig<*>.httpTimeout(block: HttpTimeoutCapabilityConfiguration.() -> Unit) =
    install(HttpTimeout, block)

/**
 * Install [contentNegotiation] plugin.
 */
fun HttpClientConfig<*>.contentNegotiation(block: ContentNegotiation.Config.() -> Unit) =
    install(ContentNegotiation, block)
