package com.msa.petsearch.shared.data.source

import com.msa.petsearch.shared.core.util.resource.asResource
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.data.source.animal.mapper.network.response.toDomainEntity
import com.msa.petsearch.shared.data.source.animal.model.network.response.PetTypesResponseDTO
import com.msa.petsearch.shared.data.source.animal.model.network.response.SearchPetResponseDTO
import com.msa.petsearch.shared.data.source.di.DataInfraNetworkTestModule
import com.msa.petsearch.shared.data.source.testfake.FakePetSearchResponseDTO
import com.msa.petsearch.shared.data.source.testfake.FakePetTypesResponseDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.awaitAllStartJobs
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(KoinExperimentalAPI::class)
@Suppress("unused")
internal class AnimalRepositoryTest : FunSpec(), KoinTest {
    private val repository by inject<AnimalRepository>()

    init {
        beforeTest {
            startKoin {
                modules(DataInfraNetworkTestModule)
            }
            getKoin().awaitAllStartJobs()
        }

        afterTest {
            stopKoin()
        }

        test("getPetTypes should return expected response") {
            repository.getPetTypes() shouldBe
                FakePetTypesResponseDTO.asResource(PetTypesResponseDTO::toDomainEntity)
        }

        test("searchPets should return expected response") {
            repository.searchPets(type = "dog", page = 1, searchParams = null) shouldBe
                FakePetSearchResponseDTO.asResource(SearchPetResponseDTO::toDomainEntity)
        }
    }
}
