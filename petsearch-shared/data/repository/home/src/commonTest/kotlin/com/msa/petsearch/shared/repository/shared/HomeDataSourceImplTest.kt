package com.msa.petsearch.shared.repository.shared

import com.msa.petsearch.shared.repository.home.HomeDataSourceImpl
import com.msa.petsearch.shared.repository.home.mapper.network.response.toDomainEntity
import com.msa.petsearch.shared.repository.shared.di.FakeHomeRepositoryModule
import com.msa.petsearch.shared.repository.shared.testfake.FakePetSearchResponseDTO
import com.msa.petsearch.shared.repository.shared.testfake.FakePetTypesResponseDTO
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@Suppress("unused")
internal class HomeDataSourceImplTest : FunSpec(), KoinTest {
    private val repository by inject<HomeDataSourceImpl>()

    init {
        beforeTest {
            startKoin {
                modules(FakeHomeRepositoryModule)
            }
        }

        afterTest {
            stopKoin()
        }

        test("getPetTypes should return expected result") {
            repository.getPetTypes().data shouldBe FakePetTypesResponseDTO.toDomainEntity()
        }

        test("searchPets should return expected result") {
            repository.searchPets(type = "Dog", page = 1, searchParams = null)
                .data shouldBe FakePetSearchResponseDTO.toDomainEntity()
        }
    }
}
