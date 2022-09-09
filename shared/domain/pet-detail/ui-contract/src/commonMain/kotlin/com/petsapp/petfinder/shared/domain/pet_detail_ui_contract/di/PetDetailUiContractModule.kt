package com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.di

import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.PetDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val PetDetailUiContractModule = module {
    singleOf(::PetDetailViewModel)
    singleOf(::PetDetailVmHelper)
}