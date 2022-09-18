object Enumerator {
    var countMessage: Int = 0
        set(value) {
            if (value > 0) field += 1
            if (value == -734) field = 0
        }

    fun clear() {
        /*
            Основной задачей счётчика является обеспечение уникальности значения,
            поэтому его сброс нежелателен в реальных условиях
        */
        countMessage = -734
    }
}