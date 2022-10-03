package com.msa.petsearch.shared.domain.petdetailuicontract.contract.mapper

import com.msa.petsearch.shared.domain.petdetailuicontract.testfake.FakeData
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class PetDetailArgsMapperTest : FunSpec({
    test("Should map State to RenderState") {
        val argsMaps = FakeData.argsMap
        val mappedState = PetDetailArgsMapper.mapArgsToState(FakeData.petDetailState, argsMaps)

        mappedState.petInfo shouldBe FakeData.petInfo
    }
})
