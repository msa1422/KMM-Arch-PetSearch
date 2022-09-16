package com.msa.petsearch.shared.datainfrastructurecache.di

import com.petsapp.petfinder.shared.datainfrastructurecache.realm.RealmDb
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val CacheInfrastructureModule = module {
    singleOf(::RealmDb)
}
