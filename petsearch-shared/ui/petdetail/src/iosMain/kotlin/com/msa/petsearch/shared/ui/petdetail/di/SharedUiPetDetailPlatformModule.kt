package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.Koin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val SharedUiPetDetailPlatformModule = module {
    singleOf(::PetDetailViewModel)
}

@Suppress("unused")
val Koin.petDetailViewModel: PetDetailViewModel
    get() = get()
