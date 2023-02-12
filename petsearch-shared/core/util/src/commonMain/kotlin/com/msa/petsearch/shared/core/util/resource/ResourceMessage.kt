package com.msa.petsearch.shared.core.util.resource

import com.benasher44.uuid.uuid4

data class ResourceMessage(
    val id: String = uuid4().toString(),
    val text: String?,
    val messageType: MessageType = MessageType.None
)
