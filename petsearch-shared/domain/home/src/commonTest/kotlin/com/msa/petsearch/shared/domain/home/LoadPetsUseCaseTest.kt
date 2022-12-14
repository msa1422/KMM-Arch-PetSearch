package com.msa.petsearch.shared.domain.home

import com.kuuurt.paging.multiplatform.PagingResult
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.domain.home.testfake.FakeAnimalRepository
import com.msa.petsearch.shared.domain.home.testfake.FakeData
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

@Suppress("UNUSED")
internal class LoadPetsUseCaseTest : FunSpec({
    lateinit var useCase: LoadPetsUseCase
    val params = LoadPetsUseCase.Params("Dog", 0, FakeData.searchParams)

    beforeTest {
        useCase = LoadPetsUseCase(FakeAnimalRepository())
    }

    test("When invoked, should return the expected response") {
        useCase.invoke(arg = params).shouldBeInstanceOf<PagingResult<Int, PetInfo>>()
    }
})
