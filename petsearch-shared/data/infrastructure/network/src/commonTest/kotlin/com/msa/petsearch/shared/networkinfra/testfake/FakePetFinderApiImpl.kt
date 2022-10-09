package com.msa.petsearch.shared.networkinfra.testfake

import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.networkinfra.EndPoints.ANIMALS
import com.msa.petsearch.shared.networkinfra.EndPoints.ANIMAL_TYPES
import com.msa.petsearch.shared.networkinfra.EndPoints.API_HOST
import com.msa.petsearch.shared.networkinfra.PetFinderApi
import com.msa.petsearch.shared.networkinfra.ktor.safeRequest
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO
import com.msa.petsearch.shared.networkinfra.response.SearchPetResponseDTO
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class FakePetFinderApiImpl : PetFinderMockEngine(), PetFinderApi {

    override suspend fun <T> getPetTypes(mapper: PetTypesResponseDTO.() -> T): Resource<T> {
        enqueueMockResponse(
            endpointSegment = "https://$API_HOST/$ANIMAL_TYPES",
            httpStatusCode = HttpStatusCode.OK,
            responseBody = Json.encodeToString(FakePetTypesResponseDTO)
        )

        return httpClient.safeRequest(mapper) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = API_HOST
                this.path(ANIMAL_TYPES)
            }
        }
    }

    override suspend fun <T> searchPets(
        parameters: HashMap<String, Any?>?,
        mapper: SearchPetResponseDTO.() -> T
    ): Resource<T> {
        enqueueMockResponse(
            endpointSegment = "https://$API_HOST/$ANIMALS",
            httpStatusCode = HttpStatusCode.OK,
            responseBody = Json.encodeToString(FakePetSearchResponseDTO)
        )

        return httpClient.safeRequest(mapper) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = API_HOST
                this.path(ANIMALS)
            }
            parameters?.forEach { entry ->
                parameter(key = entry.key, value = entry.value)
            }
        }
    }
}
