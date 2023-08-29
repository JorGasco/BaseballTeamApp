import Controllers.PlayersAPI
import Models.Player
import Utils.ScannerInput
import persistence.JSONSerializer
import java.io.File

private val players = PlayersAPI()
fun main(args: Array<String>) {
   start()
}

fun menu() : Int {
    print(""" 
         |---------- Player -------------
         |   1. Add Player
         |   2. List Players
         |   3. Search Player
         |   4. Delete Player
         |   5. Update Player
         |   6. Active Player
         |Enter Option : """.trimMargin())
    return readLine()!!.toInt()
}

fun start(){
    var input : Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> listPlayers()
            3 -> search()
            4 -> deletePlayer()
            5 -> updatePlayer()
            //6 -> setPlayerActivity()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun add() {


    val playerName = ScannerInput.readNextLine("First Name: ")
    val playerSurname = ScannerInput.readNextLine("Surname: ")
    val age = ScannerInput.readNextInt("age: ")
    val height = ScannerInput.readNextDouble("Please enter Height between 0.01 - 3.00: ")
    val weight = ScannerInput.readNextDouble("weight:")
    val position = ScannerInput.readNextLine(
        """
              > --------------------------------
              > | Write the position            |
              > |   1 - Infield                 |
              > |   2 - OutField                |
              > |   3 - Pitcher                 |
              > --------------------------------
     > ==>> """.trimMargin(">")
    )
    val isAdded: Boolean = players.add(Player(playerName = playerName, playerSurname = playerSurname, age = age, height = height,weight=weight,position=position))

    if (isAdded) {
        println("Added Franchise Successfully")
    } else {
        println("Add Failed")
    }
}

    fun listPlayers() {
        println(players.listAllPLayers())
    }

    fun getPlayerById(): Player? {
        print("Enter the Player id to search by: ")
        val playerId = readLine()!!.toInt()
        return players.findOne(playerId)
    }

    fun search() {
        val player = getPlayerById()
        if (player == null)
            println("No employee found")
        else
            println(player)
    }

    fun deletePlayer() {
        val player = getPlayerById()

        if (player != null) {
            val deleted = players.delete(player.playerId)

            if (deleted) {
                println("Player deleted successfully.")
            } else {
                println("Failed to delete Player. Player not found.")
            }
        } else {
            println("Player not found.")
        }
    }

    fun updatePlayer() {

        if (players.numberOfPlayers() > 0) {
            val id = ScannerInput.readNextInt("Enter the id of the player to update: ")
            if (players.findOne(id) != null) {

                val playerName = ScannerInput.readNextLine("First Name: ")
                val playerSurname = ScannerInput.readNextLine("Surname: ")
                val age = ScannerInput.readNextInt("age: ")
                val height = ScannerInput.readNextDouble("height: ")
                val weight = ScannerInput.readNextDouble("weight:")
                val position = ScannerInput.readNextLine("position:")

                if (players.update(id, Player(0, playerName, playerSurname, age, height, weight, position))) {
                    println("Update Successful")
                } else {
                    println("Update Failed")
                }
            } else {
                println("There are no players for this index number")
            }
        }
    }

