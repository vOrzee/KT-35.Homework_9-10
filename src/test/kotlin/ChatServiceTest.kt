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
        ChatService.createChat(500, "Пятое сообщение для 500")
        myUserId = 500
        ChatService.createMessage(385, "Первое сообщение для 385")
        ChatService.createMessage(734, "Второе сообщение для 734")
        myUserId = 734
    }

    @After
    fun tearDown() {
        Enumerator.clear()
        ChatService.clear()
        myUserId = 734
    }

    @Test
    fun getStorageChats() {
        assertEquals(3, ChatService.storageChats.count())
    }

    @Test
    fun setStorageChats() {
        ChatService.storageChats = sequenceOf()
        assertEquals(0, ChatService.storageChats.count())
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
        assertEquals(
            "Второе сообщение для 734",
            ChatService.getChats()[setOf(myUserId, 500)]
        )
    }

    @Test
    fun getChatsEmpty() {
        ChatService.deleteMessage(4)
        assertEquals(
            "Сообщений не обнаружено",
            ChatService.getChats()[setOf(myUserId, 385)]
        )
    }

    @Test
    fun getChat() {
        assertEquals(2, ChatService.getChat(500, 2).size)
    }

    @Test
    fun getChatWithoutCount() {
        println(ChatService.getChat(385))
        assertEquals(5, ChatService.getChat(500).size)
    }

    @Test
    fun createMessage() {
        ChatService.storageChats.forEach { println(it) }
        assertTrue(ChatService.createMessage(500, "Что-то там"))
    }

    @Test
    fun deleteMessage() {
        assertTrue(ChatService.deleteMessage(3))
    }

    @Test(expected = NotFoundException::class)
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
        ChatService.removeChat(500)
    }

    @Test
    fun removeChatFailed() {
        assertFalse(ChatService.removeChat(459))
    }
}