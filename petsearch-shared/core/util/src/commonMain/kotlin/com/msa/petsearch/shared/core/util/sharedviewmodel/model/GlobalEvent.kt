package com.msa.petsearch.shared.core.util.sharedviewmodel.model

import com.benasher44.uuid.uuid4
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

sealed class GlobalEvent : NanoRedux.Event {
    @Suppress("UNUSED")
    data class Idle(override val id: String = uuid4().toString()) : GlobalEvent()

    data class Loading(
        override val id: String = uuid4().toString(),
        val show: Boolean
    ) : GlobalEvent()
}
