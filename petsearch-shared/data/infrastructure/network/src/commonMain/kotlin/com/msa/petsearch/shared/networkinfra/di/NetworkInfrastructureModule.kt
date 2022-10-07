package com.msa.petsearch.shared.networkinfra.di

import com.msa.petsearch.shared.networkinfra.PetFinderApi
import com.msa.petsearch.shared.networkinfra.PetFinderApiImpl
import com.msa.petsearch.shared.networkinfra.ktor.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val NetworkInfrastructureModule = module {
    singleOf(::KtorClient)
    singleOf(::PetFinderApiImpl) bind PetFinderApi::class
}
