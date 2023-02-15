package com.msa.petsearch.shared.ui.home.testdoubles

import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.util.resource.Resource
import com.msa.petsearch.shared.core.util.resource.asResource
import com.msa.petsearch.shared.data.repository.AnimalRepository

internal class FakeAnimalRepository : AnimalRepository {

    override suspend fun getPetTypes(): Resource<PetTypesResponse?> =
        TestFake.petTypesResponse.asResource()

    override suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?) =
        if (type == "Dog") Resource.success(TestFake.petDogSearchResponse)
        else Resource.success(TestFake.petCatSearchResponse)
}
