package com.msa.petsearch.shared.networkinfra.di

import com.msa.petsearch.shared.networkinfra.NetworkDelegate
import com.msa.petsearch.shared.networkinfra.ktor.KtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NetworkInfrastructureModule = module {
    singleOf(::KtorClient)
    singleOf(::NetworkDelegate)
}
