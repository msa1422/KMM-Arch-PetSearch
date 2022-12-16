package com.msa.petsearch.shared.ui.petdetail.di

import org.koin.dsl.module

val SharedUiPetDetailModule = module {
    includes(SharedUiPetDetailPlatformModule)
}
