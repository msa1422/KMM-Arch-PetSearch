package com.msa.petsearch.shared.repository.home

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.networkinfra.NetworkDelegate
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
import com.msa.petsearch.shared.repository.home.network.EndPoints.ANIMALS
import com.msa.petsearch.shared.repository.home.network.EndPoints.ANIMAL_TYPES
import com.msa.petsearch.shared.repository.home.network.EndPoints.API_HOST
import com.msa.petsearch.shared.repository.home.network.mapper.response.toDomainEntity
import com.msa.petsearch.shared.repository.home.network.response.PetTypesResponseDTO
import com.msa.petsearch.shared.repository.home.network.response.SearchPetResponseDTO

internal class HomeDataSourceImpl(
    private val networkDelegate: NetworkDelegate
) : HomeDataSource {

    override suspend fun getPetTypes() =
        networkDelegate.get(
            host = API_HOST,
            path = arrayOf(ANIMAL_TYPES),
            mapper = PetTypesResponseDTO::toDomainEntity
        )

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

        return networkDelegate.get(
            host = API_HOST,
            path = arrayOf(ANIMALS),
            parameters = parameters,
            mapper = SearchPetResponseDTO::toDomainEntity
        )
    }
}
