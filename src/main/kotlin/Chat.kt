data class Chat(val chatId:Int, var messages:MutableList<Message>) {
    override fun equals(other: Any?): Boolean {
        if (other !is Chat) return false
        return other.chatId == this.chatId
    }

    override fun hashCode(): Int {
        return 13 * chatId
    }
}
