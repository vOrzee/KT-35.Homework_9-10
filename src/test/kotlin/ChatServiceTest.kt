import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Before
    fun setUp() {
        Enumerator.clear()
        ChatService.clear()
        myUserId = 734
        ChatService.storageChats += Chat(500, mutableListOf(
            Message("Первое сообщение для 500"),
            Message("Второе сообщение для 500"),
            Message("Третье сообщение для 500"),
            Message("Четвертое сообщение для 500"),
        ))
        myUserId = 385
        ChatService.storageChats += Chat(734, mutableListOf(Message("Первое сообщение для 734")))
        ChatService.storageChats += Chat(500, mutableListOf(Message("Первое сообщение для 500")))
        myUserId = 500
        ChatService.storageChats += Chat(385, mutableListOf(Message("Первое сообщение для 385")))
        ChatService.storageChats += Chat(734, mutableListOf(Message("Второе сообщение для 734")))
    }

    @After
    fun tearDown() {
        Enumerator.clear()
        ChatService.clear()
        myUserId = 734
    }

    @Test
    fun getStorageChats() {
        assertEquals(3, ChatService.storageChats.size)
    }

    @Test
    fun setStorageChats() {
    }

    @Test
    fun getUnreadChatCount() {
        assertEquals(3, ChatService.storageChats.size)
    }

    @Test
    fun getChats() {
        println(ChatService.getChats())
    }

    @Test
    fun getChat() {
        assertEquals(2, ChatService.getChat(500,2).size)
    }

    @Test
    fun createMessage() {
    }

    @Test
    fun deleteMessage() {
        assertTrue(ChatService.deleteMessage(3))
    }

    @Test
    fun deleteMessageFailed() {
        assertTrue(ChatService.deleteMessage(9))
    }

    @Test
    fun createChat() {
        assertTrue(ChatService.createChat(499,"Какое-то сообщение"))
    }
    @Test
    fun createChatFailed() {
        assertFalse(ChatService.createChat(500,"Какое-то сообщение"))
    }
    @Test
    fun removeChat() {
        assertTrue(ChatService.removeChat(500))
    }

    @Test
    fun removeChatFailed() {
        assertFalse(ChatService.removeChat(459))
    }
}