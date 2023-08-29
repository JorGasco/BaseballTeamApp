fun main(args: Array<String>) {
   menu()
}

fun menu() : Int {
    print(""" 
         |---------- Player -------------
         |   1. Add Player
         |   2. List Players
         |   3. Search Player
         |   4. Delete Player
         |   5. Update Player
         |   6.Active Player
         |Enter Option : """.trimMargin())
    return readLine()!!.toInt()
}