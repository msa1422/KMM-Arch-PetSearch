package com.msa.petsearch.shared.repository.home.di

import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
import com.msa.petsearch.shared.repository.home.HomeDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeRepositoryModule = module {
    singleOf(::HomeDataSourceImpl) bind HomeDataSource::class
}
