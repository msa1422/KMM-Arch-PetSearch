package com.msa.petsearch.shared.ui.home.testfake

import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.HomeUpdater
import com.msa.petsearch.shared.ui.home.HomeUseCaseWrapper
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val FakeSharedUiHomeModule = module {
    factoryOf(::FakeAnimalRepository) bind AnimalRepository::class
    factoryOf(::LoadPetsUseCase)
    factoryOf(::LoadPetTypesUseCase)
    factoryOf(::HomeUseCaseWrapper)
    factoryOf(::HomeProcessor)
    factoryOf(::HomeUpdater)
    factoryOf(::HomeViewModel)
    single { Dispatchers.Main }
}
