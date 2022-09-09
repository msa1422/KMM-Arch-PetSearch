package com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor

data class UseCaseWrapper(
    val getPetTypes: LoadPetTypesUseCase,
    val getPets: LoadPetsUseCase
)
