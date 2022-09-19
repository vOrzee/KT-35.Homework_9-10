data class Chat(val withId: Int, var messages: MutableList<Message>) {
    val chatId: Set<Int> = sortedSetOf(withId, myUserId)

    override fun equals(other: Any?): Boolean {
        if (other !is Chat) return false
        return other.chatId == this.chatId
    }

    override fun hashCode(): Int {
        return chatId.hashCode()
    }

    override fun toString(): String {
        if (chatId.size != 2) return "Chat not created"
        return "${chatId.elementAt(0)} < = > ${chatId.elementAt(1)} messages:\n$messages"
    }
}
