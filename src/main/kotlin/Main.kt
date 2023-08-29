import Utils.ScannerInput

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
            6 -> setPlayerActivity()*/
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun add() {


    val playerName  = ScannerInput.readNextLine("First Name: ")
    val playerSurname  = ScannerInput.readNextLine("Surname: ")
    val age  = ScannerInput.readNextInt("age: ")
    val height  = ScannerInput.readNextDouble("Please enter Height between 0.01 - 3.00: ")
    val weight  = ScannerInput.readNextDouble("weight:")
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


}
