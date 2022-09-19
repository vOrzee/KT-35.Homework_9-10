import org.junit.Test

import org.junit.Assert.*

class MessageTest {

    @Test
    fun getDate() {
        val message = Message("Первое сообщение")
        assertEquals(21, message.getDate().length)
        assertEquals(10, message.getDateUnixTime().toString().length)
    }

    @Test
    fun testToString() {
        val message = Message("Первое сообщение")
        assertEquals(64, message.toString().length)
    }
}