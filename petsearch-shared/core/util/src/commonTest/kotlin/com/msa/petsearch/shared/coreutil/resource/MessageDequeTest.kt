package com.msa.petsearch.shared.coreutil.resource

import com.msa.petsearch.shared.coreutil.testfake.FakeResourceMessages
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class MessageDequeTest : FunSpec({
    lateinit var messageDeque: MessageDeque

    beforeTest {
        messageDeque = MessageDeque(ArrayDeque())
    }

    test("Should enqueue message") {
        val expected = FakeResourceMessages[0]
        messageDeque.enqueue(FakeResourceMessages[0])
        messageDeque.readOnlyStateFlow().value shouldBe expected
    }

    test("Should perform Enqueue Dequeue and follow FIFO") {
        // Check count and message value
        messageDeque.count.shouldBeZero()
        messageDeque.readOnlyStateFlow().value.shouldBeNull()

        // Enqueue messages to the MessageDeque
        messageDeque.enqueue(FakeResourceMessages[0])
        messageDeque.enqueue(FakeResourceMessages[1])
        messageDeque.enqueue(FakeResourceMessages[2])

        // Check count and message value
        messageDeque.count shouldBe 3
        messageDeque.readOnlyStateFlow().value shouldBe FakeResourceMessages[0]

        // Dequeue a message from the deque
        messageDeque.dequeue()

        // Check count and message value
        messageDeque.count shouldBe 2
        messageDeque.readOnlyStateFlow().value shouldBe FakeResourceMessages[1]

        // Again dequeue a message form the deque
        messageDeque.dequeue()

        // Check count and message value
        messageDeque.count shouldBe 1
        messageDeque.readOnlyStateFlow().value shouldBe FakeResourceMessages[2]

        // Again enqueue to deque,
        // The first value should still be the one in the condition above
        messageDeque.enqueue(FakeResourceMessages[3])

        // Check count and message value
        messageDeque.count shouldBe 2
        messageDeque.readOnlyStateFlow().value shouldBe FakeResourceMessages[2]

        // Dequeue all messages
        for (i in 0 until messageDeque.count) {
            messageDeque.dequeue()
        }

        // Check count and message value
        messageDeque.count.shouldBeZero()
        messageDeque.readOnlyStateFlow().value.shouldBeNull()
    }
})
