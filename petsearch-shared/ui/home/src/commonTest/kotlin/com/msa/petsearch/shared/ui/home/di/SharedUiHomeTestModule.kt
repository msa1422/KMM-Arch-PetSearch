package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.core.util.di.CoreUtilModule
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.di.DomainHomeModule
import com.msa.petsearch.shared.ui.home.HomeUseCaseWrapper
import com.msa.petsearch.shared.ui.home.HomeViewModel
import com.msa.petsearch.shared.ui.home.testdoubles.FakeAnimalRepository
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.includes
import org.koin.dsl.bind
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
internal val SharedUiHomeTestModule = lazyModule {
    includes(DomainHomeModule, CoreUtilModule)
    factoryOf(::FakeAnimalRepository) bind AnimalRepository::class
    factoryOf(::HomeUseCaseWrapper)
    factoryOf(::HomeViewModel)
}
