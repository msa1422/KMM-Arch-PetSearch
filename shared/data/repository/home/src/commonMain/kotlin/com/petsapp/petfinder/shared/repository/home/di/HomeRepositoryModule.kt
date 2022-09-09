package com.petsapp.petfinder.shared.repository.home.di


import com.petsapp.petfinder.shared.domain.home_data_source.HomeDataSource
import com.petsapp.petfinder.shared.repository.home.HomeDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeRepositoryModule = module {
    singleOf(::HomeDataSourceImpl) bind HomeDataSource::class
}