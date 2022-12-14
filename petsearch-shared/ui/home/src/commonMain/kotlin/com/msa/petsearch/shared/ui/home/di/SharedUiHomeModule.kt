package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.domain.home.di.DomainHomeModule
import com.msa.petsearch.shared.ui.home.HomeViewModel
import com.msa.petsearch.shared.ui.home.contract.HomeProcessor
import com.msa.petsearch.shared.ui.home.contract.HomeUpdater
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SharedUiHomeModule = module {
    includes(DomainHomeModule)
    singleOf(::HomeUpdater)
    singleOf(::HomeProcessor)
    singleOf(::HomeViewModel)
    singleOf(::HomeVmHelper)
}
