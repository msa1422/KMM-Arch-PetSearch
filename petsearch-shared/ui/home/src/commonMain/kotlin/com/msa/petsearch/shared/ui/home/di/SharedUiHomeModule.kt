package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.domain.home.di.DomainHomeModule
import com.msa.petsearch.shared.ui.home.HomeUseCaseWrapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val SharedUiHomeModule = module {
    includes(DomainHomeModule)
    includes(SharedUiHomePlatformModule)

    singleOf(::HomeUseCaseWrapper)
}
