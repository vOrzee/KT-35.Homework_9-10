object ChatService {
    var storageChats = mutableSetOf<Chat>()

    fun getUnreadChatCount() = storageChats.filter { chat ->
        chat.messages.any { !it.itsRead }
    }.size

    fun getChats(): Map<Set<Int>, String> {
        val result = mutableMapOf<Set<Int>, String>()
        storageChats.forEach {
            result += if (it.messages.isEmpty()) it.chatId to "Сообщений не обнаружено"
            else it.chatId to it.messages.last().text
        }
        return result
    }

    fun getChat(withId: Int, count: Int = 100, beginId: Int? = null): List<Message> =
        storageChats.find { it.chatId == sortedSetOf(withId, myUserId) }
            ?.messages?.filter { if (beginId != null) it.id >= beginId else true }
            ?.filterIndexed { index, _ -> index < count }
            ?.onEach { it.itsRead = true } ?: emptyList()

    fun createMessage(withId: Int, text: String): Boolean {
        storageChats.find { it.chatId == sortedSetOf(withId, myUserId) }?.plus((Message(text).also {
            it.toId = withId
        })) ?: createChat(withId, text)
        return true
    }

    fun deleteMessage(messageId: Int): Boolean {
        var count = 0
        storageChats.forEach { chat ->
            if (chat.messages.any { it.id == messageId })
                count++
        }
        return if (count == 1) {
            storageChats.forEach { chat ->
                chat - chat.messages.find { it.id == messageId }
            }
            true
        } else throw NotFoundException()
    }

    fun createChat(withId: Int, text: String): Boolean =
        if (storageChats.find { it.chatId == sortedSetOf(withId, myUserId) } == null) {
            storageChats.add(Chat(withId, mutableListOf(Message(text).also { it.toId = withId })))
            true
        } else false

    fun removeChat(withId: Int): Boolean =
        storageChats.removeByChatId(withId)

    fun clear() {
        storageChats = mutableSetOf()
    }
}

fun MutableSet<Chat>.removeByChatId(withId: Int): Boolean =
    this.remove(this.find { sortedSetOf(withId, myUserId) == it.chatId })
