package utils
import models.Player
import models.Stat
object Utilities {

    @JvmStatic
    fun formatListString(PlayersToFormat: List<Player>): String =
        PlayersToFormat
            .joinToString(separator = "\n") { player -> "$player" }

    @JvmStatic
    fun formatSetString(itemsToFormat: Set<Stat>): String =
        itemsToFormat
            .joinToString(separator = "\n") { stat -> "\t$stat" }
}
