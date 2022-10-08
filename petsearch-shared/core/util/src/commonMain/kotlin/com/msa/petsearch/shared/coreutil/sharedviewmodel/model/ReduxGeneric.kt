package com.msa.petsearch.shared.coreutil.sharedviewmodel.model

import com.benasher44.uuid.uuid4
import com.msa.petsearch.shared.coreutil.resource.ResourceMessage
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux

sealed class ReduxGeneric : NanoRedux.Error {
    data class Error(override val message: ResourceMessage?) : ReduxGeneric()
}

sealed class GlobalEvent : NanoRedux.Event {
    @Suppress("UNUSED")
    data class Idle(override val id: String = uuid4().toString()) : GlobalEvent()

    data class Loading(
        override val id: String = uuid4().toString(),
        val show: Boolean
    ) : GlobalEvent()
}
