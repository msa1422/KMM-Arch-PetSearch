package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.core.util.sharedviewmodel.SavedStateHandle
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val SharedUiPetDetailPlatformModule = module {
    single { SavedStateHandle() }
    factoryOf(::PetDetailViewModel)
}

@Suppress("unused")
val Koin.PetDetailViewModel: PetDetailViewModel
    get() = get()
