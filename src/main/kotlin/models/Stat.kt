package models

class Stat(
    var hits: Int,
    var vecesAlBate: Int,
    var doubles: Int,
    var triples: Int,
    var homeRuns: Int,
    var runs: Int,
    var strikeOut: Int,
    var walks: Int,
    var statsId: Int = 0,

) {

    // total of hits to calculate the average
    fun totalHits(): Int {
        val Sum = hits + doubles + triples + homeRuns
        return Sum
    }

    fun average(): String {

        val average = totalHits() / vecesAlBate.toDouble()
        return String.format("%.3f", average)
    }

    override fun toString(): String {

        return """ 
        
        |--------------------------- Stats ------------------------------------
        |                                                    Stat ID: $statsId         
        |Veces al bate : $vecesAlBate
        |
        |Hits: $hits
        |Doubles: $doubles
        |Triples: $triples
        |HomeRuns: $homeRuns
        |
        |Runs: $runs
        |Walks: $walks
        |StrikeOuts: $strikeOut
        |                  
        |Average = ${average()}               
        |______________________________________________________________________    
        |""".trimMargin("|")
    }
}
