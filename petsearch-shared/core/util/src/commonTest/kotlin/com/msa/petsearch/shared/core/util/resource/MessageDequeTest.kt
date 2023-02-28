package com.msa.petsearch.shared.core.util.resource

import app.cash.turbine.test
import com.msa.petsearch.shared.core.util.testfake.FakeResourceMessages
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeZero
import io.kotest.matchers.shouldBe

@Suppress("UNUSED")
internal class MessageDequeTest : FunSpec({
    test("GivenResourceMessage_MessageDeque_EnqueuesMessage") {
        val messageDeque = MessageDeque
        val stateMessage = FakeResourceMessages[0]

        messageDeque().test {
            // Push the message
            messageDeque.enqueue(stateMessage)

            // await emit
            awaitItem() shouldBe stateMessage

            // dequeue the message
            messageDeque.dequeue()
        }
    }

    test("GivenSequenceOfMessages_MessageDeque_FollowsFirstInFirstOutRule") {
        val messageDeque = MessageDeque
        messageDeque().test {
            // Check count and message value
            messageDeque.count.shouldBeZero()

            // Enqueue messages to the MessageDeque
            messageDeque.enqueue(FakeResourceMessages[0])
            messageDeque.enqueue(FakeResourceMessages[1])
            messageDeque.enqueue(FakeResourceMessages[2])

            // Check count and message value
            messageDeque.count shouldBe 3
            awaitItem() shouldBe FakeResourceMessages[0]

            // Dequeue a message from the deque
            messageDeque.dequeue()

            // Check count and message value
            messageDeque.count shouldBe 2
            awaitItem() shouldBe FakeResourceMessages[1]

            // Again dequeue a message form the deque
            messageDeque.dequeue()

            // Check count and message value
            messageDeque.count shouldBe 1
            awaitItem() shouldBe FakeResourceMessages[2]

            // Again enqueue to deque,
            // The first value should still be the one in the condition above
            messageDeque.enqueue(FakeResourceMessages[3])

            // Check count and message value
            // No message shall be emitted because there is no dequeue of the last message
            messageDeque.count shouldBe 2

            // Dequeue all messages
            repeat(messageDeque.count) {
                messageDeque.dequeue()
            }

            // Last message should be emitted in the last dequeue loop
            awaitItem() shouldBe FakeResourceMessages[3]

            // Check count and message value
            messageDeque.count.shouldBeZero()
        }
    }
})
