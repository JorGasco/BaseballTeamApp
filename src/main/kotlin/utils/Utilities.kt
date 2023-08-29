package utils
import models.Stat
import models.Player
object Utilities {

    @JvmStatic
    fun formatListString(PlayersToFormat: List<Player>): String =
        PlayersToFormat
            .joinToString(separator = "\n") { player -> "$player" }

    @JvmStatic
    fun isArrayList(obj: Any): ArrayList<Player>? = if (obj is ArrayList<*> && obj.all { it is Player }) {
        @Suppress("UNCHECKED_CAST")
        obj as ArrayList<Player>
    } else {
        null
    }

    @JvmStatic
    fun formatSetString(itemsToFormat: Set<Stat>): String =
        itemsToFormat
            .joinToString(separator = "\n") { stat -> "\t$stat" }
}