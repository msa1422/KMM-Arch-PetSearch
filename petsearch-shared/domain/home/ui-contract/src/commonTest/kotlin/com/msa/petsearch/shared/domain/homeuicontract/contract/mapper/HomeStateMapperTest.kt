package com.msa.petsearch.shared.domain.homeuicontract.contract.mapper

import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import io.kotest.matchers.shouldBe
import kotlin.test.Test

internal class HomeStateMapperTest {

    @Test
    fun givenState_VerifyState_isMappedToRenderState() {
        val state = FakeData.getHomeState()
        val renderState = HomeStateMapper.mapToRenderState(state)

        renderState.petTypes shouldBe state.petTypesResponse?.types
        renderState.petPagingData shouldBe state.petPagingData
    }
}
