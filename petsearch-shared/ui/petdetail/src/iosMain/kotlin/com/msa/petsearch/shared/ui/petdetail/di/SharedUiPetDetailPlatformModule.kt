package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.core.util.sharedviewmodel.SavedStateHandle
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.Koin
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
internal actual val SharedUiPetDetailPlatformModule = lazyModule {
    single(named<PetDetailViewModel>()) {
        SavedStateHandle()
    }

    factory {
        PetDetailViewModel(get(named<PetDetailViewModel>()))
    }
}

@Suppress("unused")
val Koin.PetDetailViewModel: PetDetailViewModel
    get() = get()
