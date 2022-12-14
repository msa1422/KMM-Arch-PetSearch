package com.msa.petsearch.shared.domain.home.usecase

import com.msa.petsearch.shared.data.repository.AnimalRepository

class LoadPetTypesUseCase(private val repository: AnimalRepository) {
    suspend operator fun invoke() = repository.getPetTypes()
}
