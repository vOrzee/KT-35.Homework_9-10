object ChatService {
    var storageChats = mutableListOf<Chat>()

    fun getUnreadChatCount():Int{
        return 0
    }

    fun getChats():Map<Int,String>{
        return emptyMap()
    }

    fun getChat(chatId:Int, beginId:Int, count:Int):List<Message>{
        return emptyList()
    }

    fun createMessage(chatId: Int, text:String):Boolean{
        return true
    }

    fun deleteMessage(messageId: Int):Boolean{
        return true
    }

    fun createChat(chatId: Int):Boolean{
        return true
    }

    fun removeChat(chatId: Int):Boolean{
        return true
    }

    fun clear() {
        storageChats = mutableListOf()
    }
}