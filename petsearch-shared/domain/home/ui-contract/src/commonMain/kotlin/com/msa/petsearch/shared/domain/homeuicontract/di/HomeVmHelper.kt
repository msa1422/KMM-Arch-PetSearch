package com.msa.petsearch.shared.domain.homeuicontract.di

import com.petsapp.petfinder.shared.domain.homeuicontract.HomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeVmHelper : KoinComponent {
    private val vm by inject<HomeViewModel>()
    fun provide() = vm
}
