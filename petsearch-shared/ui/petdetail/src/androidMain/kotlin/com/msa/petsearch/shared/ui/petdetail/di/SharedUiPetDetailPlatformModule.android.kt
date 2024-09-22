package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.lazyModule

internal actual val SharedUiPetDetailPlatformModule = lazyModule {
    viewModelOf(::PetDetailViewModel)
}
