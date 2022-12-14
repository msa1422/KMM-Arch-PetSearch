package com.msa.petsearch.shared.data.source.di

import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.data.source.testfake.FakeAnimalRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val DataInfraNetworkTestModule = module {
    factoryOf(::FakeAnimalRepositoryImpl) bind AnimalRepository::class
}
