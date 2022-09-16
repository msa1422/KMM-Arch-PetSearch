package com.petsapp.petfinder.shared.datainfrastructurenetwork.di

import com.petsapp.petfinder.shared.datainfrastructurenetwork.apiservice.ApiServiceDelegate
import com.petsapp.petfinder.shared.datainfrastructurenetwork.ktor.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NetworkInfrastructureModule = module {
    singleOf(::KtorClient)
    singleOf(::ApiServiceDelegate)
}
