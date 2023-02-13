package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel2
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModelDelegate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val SharedUiPetDetailPlatformModule = module {
    singleOf(::PetDetailViewModel2)
    singleOf(::PetDetailViewModel)
    singleOf(::PetDetailViewModelDelegate)
}
