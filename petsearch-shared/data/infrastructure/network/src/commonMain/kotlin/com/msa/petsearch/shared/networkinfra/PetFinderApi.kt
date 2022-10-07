package com.msa.petsearch.shared.networkinfra

import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO
import com.msa.petsearch.shared.networkinfra.response.SearchPetResponseDTO

interface PetFinderApi {
    suspend fun <T> getPetTypes(mapper: PetTypesResponseDTO.() -> T): Resource<T>

    suspend fun <T> searchPets(
        parameters: HashMap<String, Any?>?,
        mapper: SearchPetResponseDTO.() -> T
    ): Resource<T>
}
