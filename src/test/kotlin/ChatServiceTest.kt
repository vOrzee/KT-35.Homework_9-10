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
        ChatService.storageChats += Chat(
            500, mutableListOf(
                Message("Первое сообщение для 500"),
                Message("Второе сообщение для 500"),
                Message("Третье сообщение для 500"),
                Message("Четвертое сообщение для 500"),
            )
        )
        myUserId = 385
        ChatService.createChat(734, "Первое сообщение для 734")
        ChatService.createMessage(500, "Пятое сообщение для 500")
        myUserId = 500
        ChatService.createChat(385, "Первое сообщение для 385")
        ChatService.createMessage(734, "Второе сообщение для 734")
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
        ChatService.storageChats = mutableSetOf()
        assertEquals(0, ChatService.storageChats.size)
    }

    @Test
    fun getUnreadChatCount() {
        assertEquals(3, ChatService.getUnreadChatCount())
    }
    @Test
    fun getUnreadChatCountFailed() {
        val unreadCountBefore = ChatService.getUnreadChatCount()
        ChatService.getChat(500)
        val unreadCountAfter = ChatService.getUnreadChatCount()
        assertTrue(unreadCountBefore != unreadCountAfter)
    }

    @Test
    fun getChats() {
        assertEquals("Пятое сообщение для 500",
            ChatService.getChats()[500])
    }
    @Test
    fun getChatsEmpty() {
        ChatService.deleteMessage(6)
        assertEquals("Сообщений не обнаружено",
            ChatService.getChats()[385])
    }
    @Test
    fun getChat() {
        assertEquals(2, ChatService.getChat(500, 2).size)
    }

    @Test
    fun getChatWithoutCount() {
        assertEquals(5, ChatService.getChat(500).size)
    }

    @Test
    fun createMessage() {
        assertTrue(ChatService.createMessage(500, "Что-то там"))
    }

    @Test(expected = NotFoundException::class)
    fun createMessageFailed() {
        assertFalse(ChatService.createMessage(400, "Что-то там"))
    }

    @Test
    fun deleteMessage() {
        assertTrue(ChatService.deleteMessage(3))
    }

    @Test
    fun deleteMessageFailed() {
        assertFalse(ChatService.deleteMessage(9))
    }

    @Test
    fun createChat() {
        assertTrue(ChatService.createChat(499, "Какое-то сообщение"))
    }

    @Test
    fun createChatFailed() {
        assertFalse(ChatService.createChat(500, "Какое-то сообщение"))
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