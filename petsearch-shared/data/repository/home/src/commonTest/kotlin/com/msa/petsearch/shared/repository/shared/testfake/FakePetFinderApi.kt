package com.msa.petsearch.shared.repository.shared.testfake

import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.networkinfra.PetFinderApi
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO
import com.msa.petsearch.shared.networkinfra.response.SearchPetResponseDTO

internal class FakePetFinderApi : PetFinderApi {

    override suspend fun <T> getPetTypes(mapper: PetTypesResponseDTO.() -> T): Resource<T> {
        return FakePetTypesResponseDTO.asResource { mapper(this) }
    }

    override suspend fun <T> searchPets(
        parameters: HashMap<String, Any?>?,
        mapper: SearchPetResponseDTO.() -> T
    ): Resource<T> {
        return FakePetSearchResponseDTO.asResource { mapper(this) }
    }
}
