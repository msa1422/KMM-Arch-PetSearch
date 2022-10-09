package com.msa.petsearch.shared.repository.shared.di

import com.msa.petsearch.shared.networkinfra.PetFinderApi
import com.msa.petsearch.shared.repository.home.HomeDataSourceImpl
import com.msa.petsearch.shared.repository.shared.testfake.FakePetFinderApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val FakeHomeRepositoryModule = module {
    singleOf(::HomeDataSourceImpl)
    singleOf(::FakePetFinderApi) bind PetFinderApi::class
}
