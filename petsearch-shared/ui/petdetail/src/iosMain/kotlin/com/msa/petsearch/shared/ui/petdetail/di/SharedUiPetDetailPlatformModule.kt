package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.core.util.kmmviewmodel.SavedStateHandle
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.Koin
import org.koin.dsl.module

internal actual val SharedUiPetDetailPlatformModule = module {
    single {
        val savedStateHandle = SavedStateHandle()
        PetDetailViewModel(savedStateHandle)
    }
}

@Suppress("unused")
val Koin.PetDetailViewModel: PetDetailViewModel
    get() = get()
