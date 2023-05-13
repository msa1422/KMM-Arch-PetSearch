package com.msa.petsearch.shared.core.util.resource

import com.benasher44.uuid.uuid4

data class ResourceMessage
internal constructor(
    val id: String,
    val text: String?,
    val messageType: MessageType
) {
    class Builder {
        private var id: String = uuid4().toString()
        private var text: String? = null
        private var messageType: MessageType = MessageType.None

        fun id(id: String) = apply { this.id = id }

        fun text(text: String?) = apply { this.text = text }

        fun messageType(type: MessageType) = apply { this.messageType = type }

        fun build() = ResourceMessage(id, text, messageType)
    }
}
