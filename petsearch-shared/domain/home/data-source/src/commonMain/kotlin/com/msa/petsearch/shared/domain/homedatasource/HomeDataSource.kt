package com.msa.petsearch.shared.domain.homedatasource

import com.petsapp.petfinder.shared.coreentity.PetSearchParams
import com.petsapp.petfinder.shared.coreentity.response.PetTypesResponse
import com.petsapp.petfinder.shared.coreentity.response.SearchPetResponse
import com.petsapp.petfinder.shared.coreutil.resource.Resource

interface HomeDataSource {

    suspend fun getPetTypes(): Resource<PetTypesResponse?>

    suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?):
            Resource<SearchPetResponse?>

}
