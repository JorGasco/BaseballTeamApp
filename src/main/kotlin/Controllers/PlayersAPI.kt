package Controllers

import Models.Player
import java.util.ArrayList

class PlayersAPI() {



    private var players = ArrayList<Player>()

    var lastId = 0

    private fun getId(): Int {
        return lastId++
    }

    fun add(player: Player): Boolean {
        player.playerId = getId()
        return players.add(player)
    }

    fun findOne(id: Int): Player? {
        return players.find { p -> p.playerId == id }
    }

    fun listAllPLayers() {
        if (players.isEmpty()) "There are no players"
        else formatListString(players)
    }

    fun delete(id: Int): Boolean {
        val foundPlayer = findOne(id)

        if (foundPlayer != null) {
            // Use the 'remove' function to remove the found employee from the list.
            players.remove(foundPlayer)
            return true
        }
        return false
    }

    fun update(id: Int, player: Player): Boolean {
        val foundPlayer = findOne(id)

        if (foundPlayer != null) {
            foundPlayer.playerName = player.playerName
            foundPlayer.playerSurname = player.playerSurname
            foundPlayer.age = player.age
            foundPlayer.height = player.height
            foundPlayer.weight = player.weight
            foundPlayer.position = player.position
            return true
        }
        return false
    }

    fun numberOfPlayers(): Int {
        return players.size
    }
}