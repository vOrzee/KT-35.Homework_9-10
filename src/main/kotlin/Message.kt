class Message(var text: String) : DateStamp {
    val id: Int = Enumerator.countMessage
    override val date: Int
    var itsRead: Boolean = false

    init {
        Enumerator.countMessage += 1
        date = setCurrentDate()
    }

    override fun toString(): String {
        return "сообщение $id от $date:\n$text}"
    }

}
