package com.msa.petsearch.shared.ui.home.testfake

import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.entity.response.SearchPetResponse
import com.msa.petsearch.shared.core.util.resource.Resource
import com.msa.petsearch.shared.core.util.resource.asResource
import com.msa.petsearch.shared.data.repository.AnimalRepository

internal class FakeErrorAnimalRepository : AnimalRepository {

    override suspend fun getPetTypes(): Resource<PetTypesResponse?> =
        FakeData.petTypeResponseError.asResource()

    override suspend fun searchPets(
        type: String,
        page: Int,
        searchParams: PetSearchParams?
    ): Resource<SearchPetResponse?> = FakeData.petSearchResponseError.asResource()
}
