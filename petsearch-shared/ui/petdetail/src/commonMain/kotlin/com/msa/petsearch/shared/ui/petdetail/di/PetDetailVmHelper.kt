package com.msa.petsearch.shared.ui.petdetail.di

import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PetDetailVmHelper : KoinComponent {
    private val vm by inject<PetDetailViewModel>()
    fun provide() = vm
}
