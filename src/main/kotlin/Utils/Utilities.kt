package utils

import Models.Player
object Utilities {

    @JvmStatic
    fun formatListString(notesToFormat: List<Player>): String =
        notesToFormat
            .joinToString(separator = "\n") { player -> "$player" }

}