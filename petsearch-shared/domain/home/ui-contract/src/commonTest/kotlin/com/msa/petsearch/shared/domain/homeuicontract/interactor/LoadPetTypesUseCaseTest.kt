package com.msa.petsearch.shared.domain.homeuicontract.interactor

import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeAction
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
        useCase.invoke(arg = Unit) shouldBe HomeAction.UpdatePetTypesInState(FakeData.petTypesResponse)
    }
})
