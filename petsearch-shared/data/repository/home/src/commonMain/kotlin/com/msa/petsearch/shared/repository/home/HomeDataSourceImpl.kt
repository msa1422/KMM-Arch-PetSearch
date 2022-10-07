package com.msa.petsearch.shared.repository.home

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
import com.msa.petsearch.shared.networkinfra.PetFinderApi
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO
import com.msa.petsearch.shared.networkinfra.response.SearchPetResponseDTO
import com.msa.petsearch.shared.repository.home.mapper.network.response.toDomainEntity

internal class HomeDataSourceImpl(
    private val api: PetFinderApi
) : HomeDataSource {

    override suspend fun getPetTypes() = api.getPetTypes(PetTypesResponseDTO::toDomainEntity)

    override suspend fun searchPets(
        type: String,
        page: Int,
        searchParams: PetSearchParams?
    ): Resource<SearchPetResponse?> {
        // mandatory params
        val parameters: HashMap<String, Any?> =
            hashMapOf(Pair("type", type), Pair("page", page))

        // optional params
        searchParams?.let { params ->
            parameters["sort"] = params.sort
            parameters["breed"] = params.breed?.joinToString()
            parameters["color"] = params.color?.joinToString()
            parameters["size"] = params.size?.joinToString { it.name }
            parameters["gender"] = params.gender?.joinToString { it.name }
            parameters["age"] = params.age?.joinToString { it.name }
            parameters["coat"] = params.coat?.joinToString { it.name }
            parameters["status"] = params.status?.joinToString { it.name }
            parameters["goodWithChildren"] = params.goodWithChildren
            parameters["goodWithDogs"] = params.goodWithDogs
            parameters["goodWithCats"] = params.goodWithCats
            parameters["houseTrained"] = params.houseTrained
            parameters["declawed"] = params.declawed
            parameters["specialNeeds"] = params.specialNeeds
        }

        return api.searchPets(
            parameters = parameters,
            mapper = SearchPetResponseDTO::toDomainEntity
        )
    }
}
