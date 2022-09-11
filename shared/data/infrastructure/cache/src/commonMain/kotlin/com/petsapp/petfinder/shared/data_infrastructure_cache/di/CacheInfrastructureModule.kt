package com.petsapp.petfinder.shared.data_infrastructure_cache.di

import com.petsapp.petfinder.shared.data_infrastructure_cache.realm.RealmDb
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CacheInfrastructureModule = module {
    singleOf(::RealmDb)
}
