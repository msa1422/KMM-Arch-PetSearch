package com.msa.petsearch.shared.ui.home

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModelDelegate : KoinComponent {
    val get by inject<HomeViewModel>()
}
