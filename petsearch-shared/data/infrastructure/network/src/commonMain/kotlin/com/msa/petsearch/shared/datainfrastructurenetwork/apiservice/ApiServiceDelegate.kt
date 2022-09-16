package com.msa.petsearch.shared.datainfrastructurenetwork.apiservice

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.coreentity.response.SearchPetResponse
import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.EndPoints.ANIMALS
import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.EndPoints.ANIMAL_TYPES
import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.EndPoints.API_HOST
import com.msa.petsearch.shared.datainfrastructurenetwork.ktor.safeApiCall
import com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response.toDomainEntity
import com.msa.petsearch.shared.datainfrastructurenetwork.response.PetTypesResponseDTO
import com.msa.petsearch.shared.datainfrastructurenetwork.response.SearchPetResponseDTO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.URLProtocol.Companion.HTTPS

class ApiServiceDelegate(private val httpClient: HttpClient) {

    suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?) =
        httpClient.safeApiCall<SearchPetResponseDTO, SearchPetResponse>(
            builder = {
                method = HttpMethod.Get
                url {
                    protocol = HTTPS
                    host = API_HOST
                    path(ANIMALS)
                }

                // mandatory params
                parameter("type", type)
                parameter("page", page)

                // optional params
                searchParams?.let { params ->
                    parameter("sort", params.sort)
                    parameter("breed", params.breed?.joinToString())
                    parameter("color", params.color?.joinToString())
                    parameter("size", params.size?.joinToString { it.name })
                    parameter("gender", params.gender?.joinToString { it.name })
                    parameter("age", params.age?.joinToString { it.name })
                    parameter("coat", params.coat?.joinToString { it.name })
                    parameter("status", params.status?.joinToString { it.name })
                    parameter("goodWithChildren", params.goodWithChildren)
                    parameter("goodWithDogs", params.goodWithDogs)
                    parameter("goodWithCats", params.goodWithCats)
                    parameter("houseTrained", params.houseTrained)
                    parameter("declawed", params.declawed)
                    parameter("specialNeeds", params.specialNeeds)
                }
            },
            mapper = { it.toDomainEntity() }
        )

    suspend fun getPetTypes() =
        httpClient.safeApiCall<PetTypesResponseDTO, PetTypesResponse>(
            builder = {
                method = HttpMethod.Get
                url {
                    protocol = HTTPS
                    host = API_HOST
                    path(ANIMAL_TYPES)
                }
            },
            mapper = { it.toDomainEntity() }
        )
}
