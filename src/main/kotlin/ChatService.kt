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
            ?.messages?.filter { if (beginId != null) it.id >= beginId else true }
            ?.filterIndexed { index, _ -> index < count }
            ?.onEach { it.itsRead = true } ?: emptyList()

    fun createMessage(chatId: Int, text: String): Boolean {
        storageChats.find { it.chatId == chatId }?.messages?.add(Message(text))
            ?: throw NotFoundException()
        return true
    }

    fun deleteMessage(messageId: Int): Boolean {
        var count: Int = 0
        storageChats.forEach { chat ->
            if (chat.messages.any { it.id == messageId })
                count++
        }
        storageChats.forEach { chat ->
            chat.messages.remove(chat.messages.find { it.id == messageId })
        }
        return count != 0
    }

    fun createChat(fromId: Int, text: String): Boolean =
        if (storageChats.find { it.chatId == fromId } == null) {
            storageChats.add(Chat(fromId, mutableListOf(Message(text))))
            true
        } else false

    fun removeChat(chatId: Int): Boolean =
        storageChats.removeByChatId(chatId)

    fun clear() {
        storageChats = mutableSetOf()
    }
}

fun MutableSet<Chat>.removeByChatId(chatId: Int): Boolean =
    this.remove(this.find { chatId == it.chatId })
