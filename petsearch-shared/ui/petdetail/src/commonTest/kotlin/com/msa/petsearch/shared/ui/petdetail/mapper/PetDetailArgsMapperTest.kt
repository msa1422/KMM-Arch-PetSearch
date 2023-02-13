package com.msa.petsearch.shared.ui.petdetail.mapper

import com.msa.petsearch.shared.ui.petdetail.testfake.FakeData
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class PetDetailArgsMapperTest : FunSpec({
    test("Should map State to RenderState") {
        val argsMaps = FakeData.argsMap
        val mappedState = PetDetailArgsMapper.mapArgsToState(FakeData.petDetailNavArgs, argsMaps)

        mappedState.petInfo shouldBe FakeData.petInfo
    }
})
