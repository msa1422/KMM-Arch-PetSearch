package com.msa.petsearch.shared.core.util.resource

sealed class MessageType {

    class SnackBar(
        val action: String? = null,
        val onAction: () -> Unit = {},
        val onDismiss: () -> Unit = {}
    ) : MessageType()

    object Toast : MessageType()

    object None : MessageType()
}
