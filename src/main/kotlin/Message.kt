class Message(var text: String) : DateStamp {
    val id: Int = Enumerator.countMessage
    override val date: Int
    private val fromId:Int
    var itsRead: Boolean = false

    init {
        Enumerator.countMessage += 1
        date = setCurrentDate()
        fromId = myUserId
    }

    override fun toString(): String {
        return "\n$fromId (${this.getDate()}):\n$text\nmessageID: $id\n"
    }

}
