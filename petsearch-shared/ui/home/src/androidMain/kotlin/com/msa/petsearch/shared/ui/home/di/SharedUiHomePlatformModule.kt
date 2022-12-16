package com.msa.petsearch.shared.ui.home.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.msa.petsearch.shared.ui.home.HomeViewModel
import org.koin.dsl.module

internal actual val SharedUiHomePlatformModule = module {
    viewModelOf(::HomeViewModel)
}
