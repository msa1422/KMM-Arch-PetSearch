package com.msa.petsearch.shared.ui.petdetail.di

import org.koin.core.module.includes
import org.koin.dsl.lazyModule

val SharedUiPetDetailModule = lazyModule {
    includes(SharedUiPetDetailPlatformModule)
}
