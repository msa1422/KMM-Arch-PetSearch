package com.msa.petsearch.shared.core.util.testfake

import com.msa.petsearch.shared.core.util.resource.ResourceMessage

val FakeResourceMessages: List<ResourceMessage> = listOf(
    ResourceMessage.Builder().text("Fake Message 0").build(),
    ResourceMessage.Builder().text("Fake Message 1").build(),
    ResourceMessage.Builder().text("Fake Message 2").build(),
    ResourceMessage.Builder().text("Fake Message 3").build()
)
