package com.msa.petsearch.shared.core.util.resource

sealed class MessageType {

    class SnackBar(
        val actionLabel: String?,
        val onAction: () -> Unit,
        val onDismiss: () -> Unit
    ) : MessageType() {
        class Builder {
            private var actionLabel: String? = null
            private var onAction: () -> Unit = {}
            private var onDismiss: () -> Unit = {}

            fun actionLabel(label: String?) = apply { this.actionLabel = label }

            fun onAction(block: () -> Unit) = apply { this.onAction = block }

            fun onDismiss(block: () -> Unit) = apply { this.onDismiss = block }

            fun build() = SnackBar(actionLabel, onAction, onDismiss)
        }
    }

    object Toast : MessageType()

    object None : MessageType()
}
