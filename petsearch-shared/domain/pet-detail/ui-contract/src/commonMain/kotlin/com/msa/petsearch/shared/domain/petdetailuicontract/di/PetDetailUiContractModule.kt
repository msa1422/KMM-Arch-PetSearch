package com.msa.petsearch.shared.domain.petdetailuicontract.di

import com.petsapp.petfinder.shared.domain.petdetailuicontract.PetDetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val PetDetailUiContractModule = module {
    singleOf(::PetDetailViewModel)
    singleOf(::PetDetailVmHelper)
}
