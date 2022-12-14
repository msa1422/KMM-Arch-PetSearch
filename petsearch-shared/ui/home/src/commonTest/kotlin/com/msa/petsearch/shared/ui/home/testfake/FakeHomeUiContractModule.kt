package com.msa.petsearch.shared.ui.home.testfake

import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.HomeUseCaseWrapper
import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.HomeViewModel
import com.msa.petsearch.shared.ui.home.contract.HomeProcessor
import com.msa.petsearch.shared.ui.home.contract.HomeUpdater
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val FakeSharedUiHomeModule = module {
    singleOf(::FakeAnimalRepository) bind AnimalRepository::class
    singleOf(::LoadPetsUseCase)
    singleOf(::LoadPetTypesUseCase)
    singleOf(::HomeUseCaseWrapper)
    singleOf(::HomeProcessor)
    singleOf(::HomeUpdater)
    singleOf(::HomeViewModel)
}
