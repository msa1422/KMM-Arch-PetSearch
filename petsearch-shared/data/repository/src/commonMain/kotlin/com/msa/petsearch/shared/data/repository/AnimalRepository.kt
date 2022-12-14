package com.msa.petsearch.shared.data.repository

import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.entity.response.SearchPetResponse
import com.msa.petsearch.shared.core.util.resource.Resource

interface AnimalRepository {

    suspend fun getPetTypes(): Resource<PetTypesResponse?>

    suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?):
        Resource<SearchPetResponse?>
}
