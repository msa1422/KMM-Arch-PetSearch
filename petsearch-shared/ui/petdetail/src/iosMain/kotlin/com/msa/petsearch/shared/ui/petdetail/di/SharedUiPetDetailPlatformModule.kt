package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.core.util.sharedviewmodel.SavedStateHandle
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val SavedStateHandleNamedQualifier = "PetDetailViewModel-SavedStateHandle"

internal actual val SharedUiPetDetailPlatformModule = module {
    single(named(SavedStateHandleNamedQualifier)) {
        SavedStateHandle()
    }

    factory {
        PetDetailViewModel(get(named(SavedStateHandleNamedQualifier)))
    }
}

@Suppress("unused")
val Koin.PetDetailViewModel: PetDetailViewModel
    get() = get()
