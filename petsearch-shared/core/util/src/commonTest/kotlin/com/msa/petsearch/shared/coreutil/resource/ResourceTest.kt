package com.msa.petsearch.shared.coreutil.resource

import com.msa.petsearch.shared.coreutil.testfake.FakeResourceMessages
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

@Suppress("UNUSED")
internal class ResourceTest : FunSpec({
    test("Should map to Resource object") {
        val testObj = FakeResourceMessages[0]
        // val testObjAsResource = FakeResourceMessages[0].asResource()

        FakeResourceMessages[0]
            .asResource { it }
            .shouldBeTypeOf<Resource<ResourceMessage>>()
            .data shouldBe testObj
    }
})
