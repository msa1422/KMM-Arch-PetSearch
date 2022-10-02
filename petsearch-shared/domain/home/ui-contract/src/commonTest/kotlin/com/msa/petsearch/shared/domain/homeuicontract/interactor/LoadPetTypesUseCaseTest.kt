package com.msa.petsearch.shared.domain.homeuicontract.interactor

import app.cash.turbine.test
import com.msa.petsearch.shared.coretest.suspendTest
import com.msa.petsearch.shared.coreutil.resource.asResource
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeDataSource
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

internal class LoadPetTypesUseCaseTest {

    private lateinit var useCase: LoadPetTypesUseCase

    @BeforeTest
    fun setUp() {
        useCase = LoadPetTypesUseCase(FakeHomeDataSource())
    }

    @Test
    fun wheneverLoadPetTypesUseCaseIsInvoked_ExpectedDataIsReturned() {
        suspendTest {
            val expectedResponse = FakeData.petTypesResponse.asResource { it }

            useCase.invoke(null).test {
                awaitItem() shouldBe expectedResponse
                awaitComplete()
            }
        }
    }
}