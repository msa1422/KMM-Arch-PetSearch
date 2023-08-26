package com.msa.petsearch.shared.data.source.di

import com.msa.petsearch.shared.data.infra.network.di.DataInfraNetworkModule
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.data.source.animal.AnimalRepositoryImpl
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.includes
import org.koin.dsl.bind
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val DataSourceModule = lazyModule {
    includes(DataInfraNetworkModule)
    singleOf(::AnimalRepositoryImpl) bind AnimalRepository::class
}
