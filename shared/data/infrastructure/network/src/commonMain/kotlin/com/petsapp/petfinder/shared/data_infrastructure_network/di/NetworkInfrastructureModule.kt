package com.petsapp.petfinder.shared.data_infrastructure_network.di

import com.petsapp.petfinder.shared.data_infrastructure_network.api_service.ApiServiceDelegate
import com.petsapp.petfinder.shared.data_infrastructure_network.ktor.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NetworkInfrastructureModule = module {
    singleOf(::KtorClient)
    singleOf(::ApiServiceDelegate)
}
