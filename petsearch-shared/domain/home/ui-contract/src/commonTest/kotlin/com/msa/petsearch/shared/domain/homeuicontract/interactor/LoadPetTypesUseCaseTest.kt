package com.msa.petsearch.shared.domain.homeuicontract.interactor

import app.cash.turbine.test
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeDataSource
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class LoadPetTypesUseCaseTest : FunSpec({
    lateinit var useCase: LoadPetTypesUseCase

    beforeTest {
        useCase = LoadPetTypesUseCase(FakeHomeDataSource())
    }

    test("When invoked, should return the expected response") {
        useCase.invoke(params = null).test {
            awaitItem() shouldBe FakeData.petTypesResponse.asResource { it }
            awaitComplete()
        }
    }
})
