package com.msa.petsearch.shared.datainfrastructurenetwork.di

import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.ApiServiceDelegate
import com.msa.petsearch.shared.datainfrastructurenetwork.ktor.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NetworkInfrastructureModule = module {
    singleOf(::KtorClient)
    singleOf(::ApiServiceDelegate)
}
