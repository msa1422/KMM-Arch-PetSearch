package com.msa.petsearch.shared.domain.home.di

import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.testfake.FakeAnimalRepository
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.includes
import org.koin.dsl.bind
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val DomainHomeTestModule = lazyModule {
    includes(DomainHomeModule)
    singleOf(::FakeAnimalRepository) bind  AnimalRepository::class
}
