package com.msa.petsearch.shared.domain.homeuicontract.interactor

data class UseCaseWrapper(
    val getPetTypes: LoadPetTypesUseCase,
    val getPets: LoadPetsUseCase
)
