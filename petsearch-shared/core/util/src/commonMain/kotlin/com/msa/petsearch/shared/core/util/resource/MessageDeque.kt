package com.msa.petsearch.shared.core.util.resource

import com.msa.petsearch.shared.core.util.commonflow.asCommonSharedFlow
import kotlinx.coroutines.flow.MutableSharedFlow

object MessageDeque : Queue<ResourceMessage> {
    private val deque = ArrayDeque<ResourceMessage>()
    private val message = MutableSharedFlow<ResourceMessage>()

    operator fun invoke() = message.asCommonSharedFlow()

    override val count: Int
        get() = deque.size

    override suspend fun enqueue(element: ResourceMessage): Boolean {
        return deque.none { it.id == element.id } && deque.add(element).also { peekAndUpdate() }
    }

    override suspend fun dequeue(): ResourceMessage? {
        // First remove the message from the ArrayDeque
        val transaction = deque.removeFirstOrNull()

        // Now peek and see if there are other messages in queue. If yes, then update FlowData
        peekAndUpdate()

        // Finally return the transaction data
        return transaction
    }

    override suspend fun peekAndUpdate() {
        deque.firstOrNull()?.let { message.emit(it) }
    }
}
