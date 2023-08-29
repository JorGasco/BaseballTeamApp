package Models

class Player(
    var playerId: Int = 0,
    var playerName: String,
    var playerSurname: String,
    var age: Int,
    var height: Double,
    var weight: Double,
    var position: String,
    var isActivePlayer: Boolean = false) {

    private var lastStatId = 0

    private fun getStatId(): Int {
        return lastStatId++
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