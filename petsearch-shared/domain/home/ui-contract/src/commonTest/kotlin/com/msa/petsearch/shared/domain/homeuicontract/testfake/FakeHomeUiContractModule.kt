package com.msa.petsearch.shared.domain.homeuicontract.testfake

import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
import com.msa.petsearch.shared.domain.homeuicontract.HomeViewModel
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeProcessor
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeUpdater
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetsUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.UseCaseWrapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val FakeHomeUiContractModule = module {
    singleOf(::FakeHomeDataSource) bind HomeDataSource::class
    singleOf(::LoadPetTypesUseCase)
    singleOf(::LoadPetsUseCase)
    singleOf(::UseCaseWrapper)
    singleOf(::HomeProcessor)
    singleOf(::HomeUpdater)
    singleOf(::HomeViewModel)
}
