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
            /*1 -> add()
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