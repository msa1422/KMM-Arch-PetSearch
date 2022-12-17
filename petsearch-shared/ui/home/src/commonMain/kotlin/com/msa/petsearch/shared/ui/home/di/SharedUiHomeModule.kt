package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.domain.home.di.DomainHomeModule
import com.msa.petsearch.shared.ui.home.contract.HomeProcessor
import com.msa.petsearch.shared.ui.home.contract.HomeUpdater
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SharedUiHomeModule = module {
    includes(DomainHomeModule)
    includes(SharedUiHomePlatformModule)

    single { Dispatchers.Main }
    singleOf(::HomeUpdater)
    singleOf(::HomeProcessor)
}
