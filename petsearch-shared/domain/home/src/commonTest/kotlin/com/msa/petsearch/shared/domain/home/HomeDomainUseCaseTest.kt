package com.msa.petsearch.shared.domain.home

import com.kuuurt.paging.multiplatform.PagingResult
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.resource.Resource
import com.msa.petsearch.shared.domain.home.di.DomainHomeTestModule
import com.msa.petsearch.shared.domain.home.testfake.FakeData
import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@Suppress("unused")
internal class HomeDomainUseCaseTest : KoinTest, FunSpec() {
    init {
        beforeTest {
            startKoin {
                modules(DomainHomeTestModule)
            }
        }

        afterTest {
            stopKoin()
        }

        test("LoadPetTypesUseCase_ReturnsPetTypeResponse") {
            val useCase by inject<LoadPetTypesUseCase>()

            useCase() shouldBe Resource.success(FakeData.petTypesResponse)
        }

        test("LoadPetsUseCase_ReturnsPagingResultOfPetInfoList") {
            val useCase by inject<LoadPetsUseCase>()
            val params = LoadPetsUseCase.Params("Dog", 0, FakeData.searchParams)

            useCase(params).shouldBeInstanceOf<PagingResult<Int, PetInfo>>()
                .items shouldBe FakeData.petSearchResponse.animals
        }
    }
}
