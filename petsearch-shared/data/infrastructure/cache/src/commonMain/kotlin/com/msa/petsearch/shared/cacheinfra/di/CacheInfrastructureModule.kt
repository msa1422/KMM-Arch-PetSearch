package com.msa.petsearch.shared.cacheinfra.di

import com.msa.petsearch.shared.cacheinfra.realm.RealmDb
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CacheInfrastructureModule = module {
    singleOf(::RealmDb)
}
