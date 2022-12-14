package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SharedUiPetDetailModule = module {
    singleOf(::PetDetailViewModel)
    singleOf(::PetDetailVmHelper)
}
