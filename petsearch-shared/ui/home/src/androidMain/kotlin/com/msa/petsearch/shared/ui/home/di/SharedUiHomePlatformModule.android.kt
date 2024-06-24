package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.lazyModule

internal actual val SharedUiHomePlatformModule = lazyModule {
    viewModelOf(::HomeViewModel)
}
