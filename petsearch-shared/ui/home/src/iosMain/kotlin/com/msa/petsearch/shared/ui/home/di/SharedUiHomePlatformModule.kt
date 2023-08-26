package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.ui.home.HomeViewModel
import org.koin.core.Koin
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
internal actual val SharedUiHomePlatformModule = lazyModule {
    singleOf(::HomeViewModel)
}

@Suppress("unused") // Used in Swift
val Koin.HomeViewModel: HomeViewModel
    get() = get()
