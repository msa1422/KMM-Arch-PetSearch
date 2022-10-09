package com.msa.petsearch.shared.networkinfra

import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.networkinfra.di.NetworkInfrastructureTestModule
import com.msa.petsearch.shared.networkinfra.testfake.FakePetSearchResponseDTO
import com.msa.petsearch.shared.networkinfra.testfake.FakePetTypesResponseDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@Suppress("unused")
internal class PetFinderApiTest : FunSpec(), KoinTest {
    private val petFinderApi by inject<PetFinderApi>()

    init {
        beforeTest {
            startKoin {
                modules(NetworkInfrastructureTestModule)
            }
        }

        afterTest {
            stopKoin()
        }

        test("getPetTypes should return expected response") {
            petFinderApi.getPetTypes { this@getPetTypes } shouldBe
                    FakePetTypesResponseDTO.asResource()
        }

        test("searchPets should return expected response") {
            petFinderApi.searchPets(null) { this@searchPets } shouldBe
                    FakePetSearchResponseDTO.asResource()
        }
    }
}
