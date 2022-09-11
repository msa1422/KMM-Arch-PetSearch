package com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.di

import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.PetDetailViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PetDetailVmHelper : KoinComponent {
    private val vm by inject<PetDetailViewModel>()
    fun provide() = vm
}
