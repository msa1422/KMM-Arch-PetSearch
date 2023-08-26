package com.msa.petsearch.shared.ui.petdetail.di

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.includes
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val SharedUiPetDetailModule = lazyModule {
    includes(SharedUiPetDetailPlatformModule)
}
