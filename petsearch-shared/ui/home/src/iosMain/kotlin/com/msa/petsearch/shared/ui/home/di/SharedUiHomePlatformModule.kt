package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.ui.home.HomeViewModel
import com.msa.petsearch.shared.ui.home.HomeViewModel2
import com.msa.petsearch.shared.ui.home.HomeViewModelDelegate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val SharedUiHomePlatformModule = module {
    singleOf(::HomeViewModel2)
    singleOf(::HomeViewModel)
    singleOf(::HomeViewModelDelegate)
}
