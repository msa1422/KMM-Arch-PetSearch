package com.msa.petsearch.shared.networkinfra

import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.networkinfra.EndPoints.ANIMALS
import com.msa.petsearch.shared.networkinfra.EndPoints.ANIMAL_TYPES
import com.msa.petsearch.shared.networkinfra.EndPoints.API_HOST
import com.msa.petsearch.shared.networkinfra.ktor.safeRequest
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO
import com.msa.petsearch.shared.networkinfra.response.SearchPetResponseDTO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class PetFinderApiImpl(
    private val httpClient: HttpClient
) : PetFinderApi {

    override suspend fun <T> getPetTypes(mapper: PetTypesResponseDTO.() -> T): Resource<T> {
        val response = httpClient.safeRequest<PetTypesResponseDTO> {
            method = HttpMethod.Get

            url {
                this.protocol = URLProtocol.HTTPS
                this.host = API_HOST
                this.path(ANIMAL_TYPES)
            }
        }

        return response.data?.asResource(mapper) ?: response.error.asResource()
    }

    override suspend fun <T> searchPets(
        parameters: HashMap<String, Any?>?,
        mapper: SearchPetResponseDTO.() -> T
    ): Resource<T> {
        val response = httpClient.safeRequest<SearchPetResponseDTO> {
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

        return response.data?.asResource(mapper) ?: response.error.asResource()
    }
}
