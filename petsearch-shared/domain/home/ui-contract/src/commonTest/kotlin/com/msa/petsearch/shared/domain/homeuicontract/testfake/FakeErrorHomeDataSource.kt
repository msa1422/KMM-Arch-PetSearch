package com.msa.petsearch.shared.domain.homeuicontract.testfake

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource

internal class FakeErrorHomeDataSource: HomeDataSource {

    override suspend fun getPetTypes(): Resource<PetTypesResponse?> {
        return FakeData.petTypeResponseError.asResource()
    }

    override suspend fun searchPets(
        type: String,
        page: Int,
        searchParams: PetSearchParams?
    ): Resource<SearchPetResponse?> {
        return FakeData.petSearchResponseError.asResource()
    }
}
