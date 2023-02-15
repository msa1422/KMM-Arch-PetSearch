package com.msa.petsearch.shared.domain.home.di

import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.testfake.FakeAnimalRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DomainHomeTestModule = module {
    includes(DomainHomeModule)
    singleOf(::FakeAnimalRepository) bind  AnimalRepository::class
}
