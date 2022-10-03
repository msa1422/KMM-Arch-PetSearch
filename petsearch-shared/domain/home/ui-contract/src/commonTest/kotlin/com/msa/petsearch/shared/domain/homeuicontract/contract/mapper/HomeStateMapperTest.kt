package com.msa.petsearch.shared.domain.homeuicontract.contract.mapper

import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class HomeStateMapperTest : FunSpec({
    test("Should map State to RenderState") {
        val state = FakeData.getHomeState()
        val renderState = HomeStateMapper.mapToRenderState(state)

        renderState.petTypes shouldBe state.petTypesResponse?.types
        renderState.petPagingData shouldBe state.petPagingData
    }
})
