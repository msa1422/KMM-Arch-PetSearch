package com.msa.petsearch.shared.data.source.di

import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.data.source.animal.AnimalRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DataSourceModule = module {
    singleOf(::AnimalRepositoryImpl) bind AnimalRepository::class
}
