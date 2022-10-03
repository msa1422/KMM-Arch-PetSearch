package com.msa.petsearch.shared.domain.homeuicontract.interactor

import app.cash.turbine.test
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeDataSource
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class LoadPetsUseCaseTest : FunSpec({
    lateinit var useCase: LoadPetsUseCase
    val params = LoadPetsUseCase.Params("Dog", 0, FakeData.searchParams)

    beforeTest {
        useCase = LoadPetsUseCase(FakeHomeDataSource())
    }

    test("When invoked, should return the expected response") {
        useCase.invoke(params = params).test {
            awaitItem() shouldBe FakeData.petSearchResponse.asResource { it }
            awaitComplete()
        }
    }
})
