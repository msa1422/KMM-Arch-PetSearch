package com.msa.petsearch.shared.domain.home.di

import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val DomainHomeModule = lazyModule {
    singleOf(::LoadPetTypesUseCase)
    singleOf(::LoadPetsUseCase)
}
