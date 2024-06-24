package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.domain.home.di.DomainHomeModule
import com.msa.petsearch.shared.ui.home.HomeUseCaseWrapper
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.includes
import org.koin.dsl.lazyModule

val SharedUiHomeModule = lazyModule {
    includes(DomainHomeModule, SharedUiHomePlatformModule)
    singleOf(::HomeUseCaseWrapper)
}
