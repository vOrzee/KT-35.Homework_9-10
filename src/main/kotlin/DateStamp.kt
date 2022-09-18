import java.text.SimpleDateFormat
import java.util.*

interface DateStamp {
    val date: Int
    fun getDate(): String =
        SimpleDateFormat("dd.MM.yyyy в HH:mm:ss").format(Date(date.toLong() * 1000))

    fun setCurrentDate() = (System.currentTimeMillis() / 1000).toInt()
    fun getDateUnixTime() = date
}
