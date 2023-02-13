package com.msa.petsearch.shared.ui.home

import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase

internal data class HomeUseCaseWrapper(
    val getPetTypes: LoadPetTypesUseCase,
    val getPets: LoadPetsUseCase
)
