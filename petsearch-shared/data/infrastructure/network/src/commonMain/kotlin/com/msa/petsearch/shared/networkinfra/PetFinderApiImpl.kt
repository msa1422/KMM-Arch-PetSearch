package com.msa.petsearch.shared.networkinfra

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

    override suspend fun <T> getPetTypes(mapper: PetTypesResponseDTO.() -> T) =
        httpClient.safeRequest(mapper) {
            method = HttpMethod.Get
            url {
                this.protocol = URLProtocol.HTTPS
                this.host = API_HOST
                this.path(ANIMAL_TYPES)
            }
        }

    override suspend fun <T> searchPets(
        parameters: HashMap<String, Any?>?,
        mapper: SearchPetResponseDTO.() -> T
    ) = httpClient.safeRequest(mapper) {
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
