package com.msa.petsearch.shared.data.source.testfake

import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.entity.response.SearchPetResponse
import com.msa.petsearch.shared.core.util.resource.Resource
import com.msa.petsearch.shared.data.infra.network.EndPoints.ANIMALS
import com.msa.petsearch.shared.data.infra.network.EndPoints.ANIMAL_TYPES
import com.msa.petsearch.shared.data.infra.network.EndPoints.API_HOST
import com.msa.petsearch.shared.data.infra.network.ktor.safeRequest
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.data.source.animal.mapper.network.response.toDomainEntity
import com.msa.petsearch.shared.data.source.animal.model.network.response.PetTypesResponseDTO
import com.msa.petsearch.shared.data.source.animal.model.network.response.SearchPetResponseDTO
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.path
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class FakeAnimalRepositoryImpl : PetFinderMockEngine(), AnimalRepository {

    override suspend fun getPetTypes(): Resource<PetTypesResponse?> {
        enqueueMockResponse(
            endpointSegment = "https://$API_HOST/$ANIMAL_TYPES",
            httpStatusCode = HttpStatusCode.OK,
            responseBody = Json.encodeToString(FakePetTypesResponseDTO)
        )

        return httpClient.safeRequest(PetTypesResponseDTO::toDomainEntity) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = API_HOST
                this.path(ANIMAL_TYPES)
            }
        }
    }

    override suspend fun searchPets(
        type: String,
        page: Int,
        searchParams: PetSearchParams?
    ): Resource<SearchPetResponse?> {
        enqueueMockResponse(
            endpointSegment = "https://$API_HOST/$ANIMALS?type=$type&page=$page",
            httpStatusCode = HttpStatusCode.OK,
            responseBody = Json.encodeToString(FakePetSearchResponseDTO)
        )

        return httpClient.safeRequest(SearchPetResponseDTO::toDomainEntity) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = API_HOST
                this.path(ANIMALS)
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
}
