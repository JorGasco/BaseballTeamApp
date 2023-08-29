package Controllers

import Models.Player
import java.util.ArrayList

class PlayerAPI() {



    private var players = ArrayList<Player>()

    var lastId = 0

    private fun getId(): Int {
        return lastId++
    }

    fun add(player: Player): Boolean {
        player.playerId = getId()
        return players.add(player)
    }
}