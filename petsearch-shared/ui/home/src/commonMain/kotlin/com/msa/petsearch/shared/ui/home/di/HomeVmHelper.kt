package com.msa.petsearch.shared.ui.home.di

import com.msa.petsearch.shared.ui.home.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeVmHelper : KoinComponent {
    private val vm by inject<HomeViewModel>()
    fun provide() = vm
}
