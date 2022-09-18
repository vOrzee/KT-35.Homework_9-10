import org.junit.Test

import org.junit.Assert.*

class ChatTest {

    @Test
    fun testEquals() {
        val chat1 = Chat(86, mutableListOf(Message("AB")))
        val chat2 = Chat(86, mutableListOf(Message("AC")))
        val chat3 = Chat(88, mutableListOf(Message("AC")))
        val message = Message("TEXT")
        assertTrue(chat1==chat2)
        assertTrue(chat2 != chat3 && chat1 != chat3)
        assertTrue(!chat2.equals(message))
    }
}