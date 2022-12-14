package com.msa.petsearch.shared.domain.home

import com.msa.petsearch.shared.core.util.resource.asResource
import com.msa.petsearch.shared.domain.home.testfake.FakeAnimalRepository
import com.msa.petsearch.shared.domain.home.testfake.FakeData
import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class LoadPetTypesUseCaseTest : FunSpec({
    lateinit var useCase: LoadPetTypesUseCase

    beforeTest {
        useCase = LoadPetTypesUseCase(FakeAnimalRepository())
    }

    test("When invoked, should return the expected response") {
        useCase.invoke() shouldBe FakeData.petTypesResponse.asResource()
    }
})
