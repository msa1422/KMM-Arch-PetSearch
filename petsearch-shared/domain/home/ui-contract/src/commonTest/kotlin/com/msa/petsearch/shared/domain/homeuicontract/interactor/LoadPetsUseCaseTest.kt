package com.msa.petsearch.shared.domain.homeuicontract.interactor

import com.kuuurt.paging.multiplatform.PagingResult
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeDataSource
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeInstanceOf

@Suppress("UNUSED")
internal class LoadPetsUseCaseTest : FunSpec({
    lateinit var useCase: LoadPetsUseCase
    val params = LoadPetsUseCase.Params("Dog", 0, FakeData.searchParams)

    beforeTest {
        useCase = LoadPetsUseCase(FakeHomeDataSource())
    }

    test("When invoked, should return the expected response") {
        useCase.invoke(arg = params).shouldBeInstanceOf<PagingResult<Int, PetInfo>>()
    }
})
