package com.msa.petsearch.shared.ui.petdetail

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PetDetailViewModelDelegate : KoinComponent {
    val get by inject<PetDetailViewModel>()
}
