package com.petsapp.petfinder.shared.domain.home_ui_contract.di

import com.petsapp.petfinder.shared.domain.home_ui_contract.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeVmHelper : KoinComponent {
    private val vm by inject<HomeViewModel>()
    fun provide() = vm
}
