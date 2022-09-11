package com.petsapp.petfinder.shared.domain.home_ui_contract.di

import com.petsapp.petfinder.shared.domain.home_ui_contract.HomeViewModel
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.HomeProcessor
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.HomeUpdater
import com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor.LoadPetTypesUseCase
import com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor.LoadPetsUseCase
import com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor.UseCaseWrapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val HomeUiContractModule = module {

    singleOf(::LoadPetTypesUseCase)
    singleOf(::LoadPetsUseCase)
    singleOf(::UseCaseWrapper)

    singleOf(::HomeUpdater)
    singleOf(::HomeProcessor)
    singleOf(::HomeViewModel)
    singleOf(::HomeVmHelper)

}
