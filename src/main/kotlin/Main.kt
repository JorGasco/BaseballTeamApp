import Controllers.PlayersAPI
import Models.Player
import Utils.ScannerInput
import models.Stat
import persistence.JSONSerializer
import java.io.File

private val players = PlayersAPI(JSONSerializer(File("players.json")))
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
         |   
         |--------- Stats ---------------
         |   7. Add Stat
         |   8. List Stats
         |   9. Delete Stat
         |   10.Update Stat
         |   11. Search Stat
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

            7-> addStats()
            8-> listStats()
            9-> deleteStat()
            10->updateStat()
            11->searchStat()

            19->load()
            20->save()
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
///////////////////////////////// STAts

internal fun getStatsById(player: Player): Stat? {
    print("Enter the Stat id to search by: ")
    val statId = readLine()!!.toInt()
    return player.findOne(statId)
}

fun addStats() {

    val player: Player? = getPlayerById()
    if (player != null){
        if (player.add(Stat(
                hits = ScannerInput.readNextInt("\t Hits: ") ,
                vecesAlBate = ScannerInput.readNextInt("\t Number at Bat: ") ,
                doubles = ScannerInput.readNextInt("\t Doubles: ") ,
                triples = ScannerInput.readNextInt("\t Triples: ") ,
                homeRuns = ScannerInput.readNextInt("\t HomeRuns: ") ,
                strikeOut = ScannerInput.readNextInt("\t StrikeOuts: "),
                walks = ScannerInput.readNextInt("\t Walks: "),
                runs = ScannerInput.readNextInt("\t Runs: ")
            )
            )
        )
            println("Added Successfully!")
        else println("Add NOT Successful")
    }
}

fun deleteStat() {
    val player: Player? = getPlayerById()
    if (player != null) {
        val stat: Stat? = getStatsById(player)
        if (stat != null) {
            val isDeleted = player.delete(stat.statsId)
            if (isDeleted) {
                println("Delete Successful!")
            } else {
                println("Delete NOT Successful")
            }
        }
    }
}

fun updateStat() {
    val player: Player? = getPlayerById()
    if (player != null) {
        val stat: Stat? = getStatsById(player)
        if (stat != null) {
            val hits = ScannerInput.readNextInt("Hits: ")
            val vecesAlBate = ScannerInput.readNextInt("Veces al bate: ")
            val doubles = ScannerInput.readNextInt("\t Doubles: ")
            val triples = ScannerInput.readNextInt("\t Triples: ")
            val homeRuns = ScannerInput.readNextInt("\t HomeRuns: ")
            val strikeOut = ScannerInput.readNextInt("\t StrikeOuts: ")
            val walks = ScannerInput.readNextInt("\t Walks: ")
            val runs = ScannerInput.readNextInt("\t Runs: ")

            if (player.update(stat.statsId, Stat(hits = hits,
                    vecesAlBate = vecesAlBate,
                    doubles = doubles,
                    triples = triples,
                    homeRuns = homeRuns,
                    strikeOut = strikeOut,
                    walks = walks,
                    runs = runs)
                )) {
                println("Game updated")
            } else {
                println("Game NOT updated")
            }
        } else {
            println("Invalid Game Id")
        }
    }
}

fun searchStat() {

    val player = getPlayerById()
    if (player != null) {
        val stat: Stat? = getStatsById(player)
        if (stat != null) {
            println(stat)
        }else println("stat NOT updated")
    }else println("ID NOT ")
}


fun listStats() = println(players.listAllStats())

fun save() {
    try {
        players.save()
        println("Save Successful")
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        players.load()
        println("Load Successful")
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}