import java.io.Serializable

object ChatService {
    var storageChats = mutableSetOf<Chat>()

    fun getUnreadChatCount() = storageChats.filter { chat ->
        chat.messages.any { !it.itsRead }
    }.size

    fun getChats(): Map<Int, String> {
        val result = mutableMapOf<Int, String>()
        storageChats.forEach {
            result += if (it.messages.isEmpty()) it.chatId to "Сообщений не обнаружено"
            else it.chatId to it.messages.last().text
        }
        return result
    }

    fun getChat(chatId: Int, count: Int = 100, beginId: Int? = null): List<Message> =
        storageChats.find { it.chatId == chatId }
            ?.messages?.filter { if (beginId != null) it.id <= beginId else true }
            ?.filterIndexed { index, _ -> index < count }
            ?.onEach { it.itsRead = true } ?: emptyList()

    fun createMessage(chatId: Int, text: String): Boolean {
        return true
    }

    fun deleteMessage(messageId: Int): Boolean {
        return true
    }

    fun createChat(chatId: Int): Boolean {
        return true
    }

    fun removeChat(chatId: Int): Boolean {
        return true
    }

    fun clear() {
        storageChats = mutableSetOf()
    }
}
