package com.msa.petsearch.shared.data.source.animal

import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.data.infra.network.EndPoints
import com.msa.petsearch.shared.data.infra.network.ktor.safeRequest
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.data.source.animal.mapper.network.response.toDomainEntity
import com.msa.petsearch.shared.data.source.animal.model.network.response.PetTypesResponseDTO
import com.msa.petsearch.shared.data.source.animal.model.network.response.SearchPetResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.path

internal class AnimalRepositoryImpl(private val httpClient: HttpClient) : AnimalRepository {

    override suspend fun getPetTypes() =
        httpClient.safeRequest(PetTypesResponseDTO::toDomainEntity) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = EndPoints.API_HOST
                this.path(EndPoints.ANIMAL_TYPES)
            }
        }

    override suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?) =
        httpClient.safeRequest(SearchPetResponseDTO::toDomainEntity) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = EndPoints.API_HOST
                this.path(EndPoints.ANIMALS)
            }

            // mandatory params
            parameter(key = "type", value = type)
            parameter(key = "page", value = page)

            // optional params
            searchParams?.let { params ->
                parameter(key = "sort", value = params.sort)
                parameter(key = "breed", value = params.breed?.joinToString())
                parameter(key = "color", value = params.color?.joinToString())
                parameter(key = "size", value = params.size?.joinToString { it.name })
                parameter(key = "gender", value = params.gender?.joinToString { it.name })
                parameter(key = "age", value = params.age?.joinToString { it.name })
                parameter(key = "coat", value = params.coat?.joinToString { it.name })
                parameter(key = "status", value = params.status?.joinToString { it.name })
                parameter(key = "goodWithChildren", value = params.goodWithChildren)
                parameter(key = "goodWithDogs", value = params.goodWithDogs)
                parameter(key = "goodWithCats", value = params.goodWithCats)
                parameter(key = "houseTrained", value = params.houseTrained)
                parameter(key = "declawed", value = params.declawed)
                parameter(key = "specialNeeds", value = params.specialNeeds)
            }
        }
}
