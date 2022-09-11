package com.petsapp.petfinder.shared.core_util.resource

sealed class MessageType {

    class SnackBar(
        val action: String? = null,
        val onAction: () -> Unit = {},
        val onDismiss: () -> Unit = {}
    ) : MessageType()

    object Toast : MessageType()

    object None : MessageType()
}
