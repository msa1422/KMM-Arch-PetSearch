package com.msa.petsearch.shared.domain.homeuicontract.interactor

import app.cash.turbine.test
import com.msa.petsearch.shared.coretest.suspendTest
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeDataSource
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class LoadPetsUseCaseTest {

    private lateinit var useCase: LoadPetsUseCase

    @BeforeTest
    fun setUp() {
        useCase = LoadPetsUseCase(FakeHomeDataSource())
    }

    @Test
    fun wheneverLoadPetTypesUseCaseIsInvoked_ExpectedDataIsReturned() {
        suspendTest {
            val expectedResponse = FakeData.petSearchResponse.asResource { it }

            useCase.invoke(
                params = LoadPetsUseCase.Params("Dog", 0, FakeData.searchParams)
            ).test {
                awaitItem() shouldBe expectedResponse
                awaitComplete()
            }
        }
    }
}
