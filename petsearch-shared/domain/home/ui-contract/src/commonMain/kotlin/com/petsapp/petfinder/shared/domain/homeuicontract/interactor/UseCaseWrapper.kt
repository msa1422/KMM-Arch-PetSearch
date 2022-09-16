package com.petsapp.petfinder.shared.domain.homeuicontract.interactor

data class UseCaseWrapper(
    val getPetTypes: LoadPetTypesUseCase,
    val getPets: LoadPetsUseCase
)
