package models

import utils.Utilities

data class Player(
    var playerId: Int = 0,
    var playerName: String,
    var playerSurname: String,
    var age: Int,
    var height: Double,
    var weight: Double,
    var position: String,
    var isActivePlayer: Boolean = false,
    var stats: MutableSet<Stat> = mutableSetOf()
) {

    private var lastStatId = 0

    private fun getStatId(): Int {
        return lastStatId++
    }

    fun addStat(stat: Stat): Boolean {
        lastStatId = stats.size
        stat.statsId = getStatId()
        return stats.add(stat)
    }

    fun amountOfStats() = stats.size

    fun findOne(id: Int): Stat? {
        return stats.find { stat -> stat.statsId == id }
    }

    fun delete(id: Int): Boolean {
        val foundStat = findOne(id)

        if (foundStat != null) {
            // Use the 'remove' function to remove the found employee from the list.
            stats.remove(foundStat)
            return true
        }
        return false
    }

    fun update(id: Int, stat: Stat): Boolean {
        val foundStat = findOne(id)

        if (foundStat != null) {
            foundStat.hits = stat.hits
            foundStat.vecesAlBate = stat.vecesAlBate
            foundStat.walks = stat.walks
            foundStat.runs = stat.runs
            foundStat.strikeOut = stat.strikeOut
            foundStat.doubles = stat.doubles
            foundStat.triples = stat.triples
            foundStat.homeRuns = stat.homeRuns
            return true
        }
        return false
    }
    fun listStats() =
        if (stats.isEmpty()) "\tNo Games Added"
        else Utilities.formatSetString(stats)

    fun statSize() = stats.size

    fun getFullName(): String {
        val fullName = "$playerName $playerSurname"
        return fullName
    }

    override fun toString(): String {
        return """
        |----------------------------- Player --------------------------------
        |Name:${getFullName()}                                 ID: $playerId     
        |Age : $age Years old               Active Player: $isActivePlayer
        |Height : $height m                            Position: $position
        |Weight : $weight Kg
            
        """.trimMargin("|")
    }
}
