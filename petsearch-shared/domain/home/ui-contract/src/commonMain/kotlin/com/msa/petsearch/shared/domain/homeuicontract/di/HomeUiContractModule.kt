package com.msa.petsearch.shared.domain.homeuicontract.di

import com.msa.petsearch.shared.domain.homeuicontract.HomeViewModel
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeProcessor
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeUpdater
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetsUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.UseCaseWrapper
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
