package com.petsapp.petfinder.shared.domain.home_data_source

import com.petsapp.petfinder.shared.core_entity.PetSearchParams
import com.petsapp.petfinder.shared.core_entity.response.PetTypesResponse
import com.petsapp.petfinder.shared.core_entity.response.SearchPetResponse
import com.petsapp.petfinder.shared.core_util.resource.Resource

interface HomeDataSource {

    suspend fun getPetTypes(): Resource<PetTypesResponse?>

    suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?):
            Resource<SearchPetResponse?>

}