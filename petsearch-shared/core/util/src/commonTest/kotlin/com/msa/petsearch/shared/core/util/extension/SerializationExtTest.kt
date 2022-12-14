package com.msa.petsearch.shared.core.util.extension

import com.msa.petsearch.shared.core.util.testfake.DummySerializationTestObj
import io.kotest.assertions.json.shouldBeValidJson
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class SerializationExtTest : FunSpec({
    test("Should Serialize and Deserialize") {
        val testObj = DummySerializationTestObj()

        val encodedStr = testObj.encodeToString()
        encodedStr.shouldBeValidJson()

        val decodedStr: DummySerializationTestObj = encodedStr.decodeFromString()
        decodedStr shouldBe testObj
    }
})
