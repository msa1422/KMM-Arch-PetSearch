package com.msa.petsearch.shared.data.infra.network.di

import com.msa.petsearch.shared.data.infra.network.ktor.KtorClient
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val DataInfraNetworkModule = lazyModule {
    singleOf(::KtorClient)
}
