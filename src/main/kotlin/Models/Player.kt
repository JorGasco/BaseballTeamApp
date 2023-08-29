package Models

import models.Stat

class Player(
    var playerId: Int = 0,
    var playerName: String,
    var playerSurname: String,
    var age: Int,
    var height: Double,
    var weight: Double,
    var position: String,
    var isActivePlayer: Boolean = false
    var stats: MutableSet<Stat> = mutableSetOf()){

    private var lastStatId = 0

    private fun getStatId(): Int {
        return lastStatId++
    }

    fun add(stat: Stat): Boolean  {
        stat.statsId = getStatId()
        return stats.add(stat)
    }

    fun findOne(id: Int): Stat? {
        return stats.find { p -> p.statsId == id }
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
            return true
        }
        return false
    }

    fun getFullName(): String{
        val fullName = "${playerName} ${playerSurname}"
        return  fullName
    }

    override fun toString(): String {
        return """
        |______________________________________________________________________
        |Name:${getFullName()}                                 ID: ${playerId}     
        |Age : ${age} Years old               Active Player: ${isActivePlayer}
        |Height : ${height} m                            Position: ${position}
        |Weight : ${weight} Kg
        |______________________________________________________________________    
        """.trimMargin("|")
    }
}