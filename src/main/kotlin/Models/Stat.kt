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


        fun average(): Double {
            // Check if vecesAlBate is not zero to avoid division by zero
            if (vecesAlBate != 0 && vecesAlBate>totalHits()) {
                val result = totalHits() / vecesAlBate.toDouble()
                // Format the result with three decimal places
                return result
            } else {
                // Return 0.000 if vecesAlBate is zero or if hits is more than the number at Bats
                throw IllegalArgumentException("Invalid vecesAlBate or totalHits values")
            }
        }




    }



