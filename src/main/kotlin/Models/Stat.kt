package models

class Stat (var vecesAlBate: Int,
            var hits: Int,
            var doubles:Int,
            var triples: Int,
            var homeRuns:Int,
            var runs: Int,
            var strikeOut: Int,
            var walks: Int,
            var statsId: Int = 0,
            var statAtDate: Boolean = false
) {

        // total of hits to calculate the average
        fun totalHits(): Int{
            val Sum = hits+doubles+triples+homeRuns
            return Sum
        }





}