package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel2
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal actual val SharedUiPetDetailPlatformModule = module {
    viewModelOf(::PetDetailViewModel2)
    viewModelOf(::PetDetailViewModel)
}
