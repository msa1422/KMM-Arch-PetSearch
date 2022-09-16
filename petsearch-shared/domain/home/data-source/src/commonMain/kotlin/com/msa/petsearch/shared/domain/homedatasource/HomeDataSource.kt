package com.msa.petsearch.shared.domain.homedatasource

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.coreutil.resource.Resource

interface HomeDataSource {

    suspend fun getPetTypes(): Resource<PetTypesResponse?>

    suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?):
            Resource<SearchPetResponse?>

}
