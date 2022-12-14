package com.msa.petsearch.shared.core.util.testfake

import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable

@Serializable
internal data class DummySerializationTestObj(
    val id: String = uuid4().toString(),
    val dummyText: String? = "This is a dummy test",
    val dummyMessage: String = "This is a dummy Message"
)
