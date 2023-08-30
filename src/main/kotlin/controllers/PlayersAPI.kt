package controllers
import models.Player
import models.Stat
import utils.Utilities.formatListString

import persistence.Serializer
import utils.Utilities.isArrayList

import java.util.ArrayList



class PlayersAPI(serializerType: Serializer) {

    private var serializer: Serializer = serializerType

    private var players = ArrayList<Player>()

    private var lastId = 0

    private fun getId(): Int {
        return lastId++
    }

    fun add(player: Player): Boolean {
        player.playerId = getId()
        return players.add(player)
    }

    //List
    fun findAll(): List<Player> {
        return players
    }

    fun findOne(id: Int): Player? {
        return players.find { p -> p.playerId == id }
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


    fun listAllStats() =
        if (players.isEmpty()) "There are no Players and no Stats"
        else {
            var listOfStats = ""
            for (player in players) {
                for (stat in player.stats) {
                    listOfStats += "${player} \n\t${stat}\n"
                }
            }
            if (listOfStats == "") "No Stats found in Players"
            else listOfStats
        }


    @Throws(Exception::class)
    fun save() {
        serializer.write(players)
    }

    @Throws(Exception::class)
    fun load() {
        @Suppress("UNCHECKED_CAST")
        players = serializer.read() as java.util.ArrayList<Player>
        lastId = players.size
    }



    fun listAllPLayers() =
        if (players.isEmpty()) "There are no players"
    else formatListString(players)


    fun searchByPositions(searchString: String) =
        formatListString(players.filter { player -> player.position.contains(searchString, ignoreCase = true) })

    fun searchPlayerName(searchString: String) =
        formatListString(players.filter { player -> player.playerName.contains(searchString, ignoreCase = true) })

    fun numberOfPlayers(): Int {
        return players.size
    }

    fun searchHits(searchInt: Int): String {
        return if (numberOfPlayers() == 0) {
            "No Players available"
        } else {
            var listOfPlayers = ""
            for (player in players) {
                for (stat in player.stats) {
                    if (stat.hits <= searchInt) {
                        listOfPlayers += "${player.playerId}: ${player.getFullName()} \n\t${stat}\n"
                    }
                }
            }
            if (listOfPlayers == "") "No Stats found : $searchInt"
            else listOfPlayers
        }
    }

    fun averageAge(): String {
        val totalAge = players.sumBy { it.age }
        val average = totalAge.toDouble() / players.size
        return String.format("%.2f", average)

    }

    fun averageWeight(): String {
        val totalWeight = players.sumByDouble { it.weight }
        val average = totalWeight.toDouble() / players.size
        return String.format("%.2f", average)

    }

    fun averageHeight(): String {
        val totalHeight = players.sumByDouble { it.height }
        val average = totalHeight.toDouble() / players.size
        return String.format("%.2f", average)

    }

    fun averagePlayerStats(): String {
        val totalPlayers = players.size

        // Initialize variables to store the sum of each statistic
        var totalHits = 0
        var totalDoubles = 0
        var totalTriples = 0
        var totalHomeRuns = 0
        var totalRuns = 0
        var totalStrikeouts = 0
        var totalWalks = 0

        // Calculate the sum of each statistic across all players
        for (player in players) {
            totalHits += player.stats.sumBy { it.hits }
            totalDoubles += player.stats.sumBy { it.doubles }
            totalTriples += player.stats.sumBy { it.triples }
            totalHomeRuns += player.stats.sumBy { it.homeRuns }
            totalRuns += player.stats.sumBy { it.runs }
            totalStrikeouts += player.stats.sumBy { it.strikeOut }
            totalWalks += player.stats.sumBy { it.walks }
        }

        // Calculate the averages
        val averageHits = totalHits.toDouble() / totalPlayers
        val averageDoubles = totalDoubles.toDouble() / totalPlayers
        val averageTriples = totalTriples.toDouble() / totalPlayers
        val averageHomeRuns = totalHomeRuns.toDouble() / totalPlayers
        val averageRuns = totalRuns.toDouble() / totalPlayers
        val averageStrikeouts = totalStrikeouts.toDouble() / totalPlayers
        val averageWalks = totalWalks.toDouble() / totalPlayers

        val stringHits = String.format("%.2f", averageHits)
        val stringaverageDoubles = String.format("%.2f", averageDoubles)
        val stringaverageTriples = String.format("%.2f", averageTriples)
        val stringaverageHomeRuns = String.format("%.2f", averageHomeRuns)
        val stringaverageRuns = String.format("%.2f", averageRuns)
        val stringaverageStrikeouts = String.format("%.2f", averageStrikeouts)
        val stringaverageWalks = String.format("%.2f", averageWalks)


        // Create a formatted string to display the averages
        return """
        |-------------------------------------
        |Average Hits: $stringHits
        |Average Doubles: $stringaverageDoubles
        |Average Triples: $stringaverageTriples
        |Average Home Runs: $stringaverageHomeRuns
        |Average Runs: $stringaverageRuns
        |Average Strikeouts: $stringaverageStrikeouts
        |Average Walks: $stringaverageWalks
        |-------------------------------------
    """.trimMargin("|")
    }

    fun findPlayer(index: Int): Player? {
        return if (isValidListIndex(index, players)) {
            players[index]
        } else null
    }

    //utility method to determine if an index is valid in a list.
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}























