package com.msa.petsearch.shared.domain.petdetailuicontract.contract.mapper

import com.msa.petsearch.shared.domain.petdetailuicontract.testfake.FakeData
import io.kotest.matchers.shouldBe
import kotlin.test.Test

internal class PetDetailArgsMapperTest {

    @Test
    fun givenMapOfArgs_VerifyArgs_isMappedToState() {
        val argsMaps = FakeData.argsMap
        val initialState = FakeData.petDetailState

        val mappedState = PetDetailArgsMapper.mapArgsToState(initialState, argsMaps)

        mappedState.petInfo shouldBe FakeData.petInfo
    }
}