package controllers

import models.Player
import models.Stat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayerTest {
    private var Jorge: Player? = null
    private var Patty: Player? = null
    private var Diego: Player? = null
    private var Dani: Player? = null
    private var Gabriel: Player? = null

    private var populatedPlayers: PlayersAPI? = PlayersAPI(JSONSerializer(File("players.json")))
    private var emptyPlayers: PlayersAPI? = PlayersAPI(JSONSerializer(File("players.json")))

    @BeforeEach
    fun setup() {
        Jorge = Player(0, "Jorge", "Gasco", 25, 25.00, 25.00, "outfield", false)
        Patty = Player(1, "Patty", "Gasco", 25, 25.00, 25.00, "infield", false)
        Diego = Player(2, "Diego", "Gasco", 25, 25.00, 25.00, "outfield", false)
        Dani = Player(3, "Dani", "Gasco", 25, 25.00, 25.00, "Pitcher", false)
        Gabriel = Player(4, "Gabriel", "Gasco", 25, 25.00, 25.00, "Pitcher", false)
       // added for the list



        Jorge!!.stats.add(Stat(1, 2, 3, 4, 5, 6, 7, 8, 0))
        Patty!!.stats.add(Stat(1, 2, 3, 4, 5, 6, 7, 8, 0))
        Diego!!.stats.add(Stat(1, 2, 3, 4, 5, 6, 7, 8, 0))
        Dani!!.stats.add(Stat(1, 2, 3, 4, 5, 6, 7, 8, 0))
        Gabriel!!.stats.add(Stat(1, 2, 3, 4, 5, 6, 7, 8, 0))

        //adding 5 Player to the Players api
        populatedPlayers!!.add(Jorge!!)
        populatedPlayers!!.add(Patty!!)
        populatedPlayers!!.add(Diego!!)
        populatedPlayers!!.add(Dani!!)
        populatedPlayers!!.add(Gabriel!!)
    }

        @AfterEach
        fun tearDown() {
            Jorge = null
            Patty = null
            Diego = null
            Dani = null
            Gabriel = null
            populatedPlayers = null
            emptyPlayers = null
        }
    @Nested
    inner class AddStat {
        @Test
        fun `adding a Stat to a Player in populated list adds to set`() {
            val newStat = Stat(1, 2, 3, 4, 5, 6, 7, 8, 5)
            assertEquals(5, populatedPlayers!!.numberOfStats())
            assertTrue(Jorge!!.addStat(newStat))
            assertEquals(6, populatedPlayers!!.numberOfStats())
            assertEquals(newStat, Jorge!!.findOne(Jorge!!.statSize() - 1))
        }
    }
    @Nested
    inner class UpdateStats {
        @Test
        fun `updating a Stat that does not exist returns false`() {
            assertFalse(Jorge!!.update(6, Stat(1, 2, 3, 4, 5, 6, 7, 8, 0)))
            assertFalse(Jorge!!.update(-1, Stat(1, 2, 3, 4, 5, 6, 7, 8, 0)))
            assertFalse(Jorge!!.update(2, Stat(1, 2, 3, 4, 5, 6, 7, 8, 0)))
        }

        @Test
        fun `updating a game that exists returns true and updates`() {
            // check franchise 5 exists and check the games
            assertEquals(Gabriel, populatedPlayers!!.findPlayer(4))
            assertEquals(1, Gabriel!!.findOne(0)!!.hits)
            assertEquals(2, Gabriel!!.findOne(0)!!.vecesAlBate)
            assertEquals(3, Gabriel!!.findOne(0)!!.doubles)
            assertEquals(4, Gabriel!!.findOne(0)!!.triples)
            assertEquals(5, Gabriel!!.findOne(0)!!.homeRuns)
            assertEquals(6, Gabriel!!.findOne(0)!!.runs)
            assertEquals(7, Gabriel!!.findOne(0)!!.strikeOut)
            assertEquals(8, Gabriel!!.findOne(0)!!.walks)


            // update franchise 5 with new game information and ensure contents updated successfully
            assertTrue(Gabriel!!.update(0, Stat(1, 2, 3, 4, 5, 6, 7, 8, 0)))
            assertEquals(1, Gabriel!!.findOne(0)!!.hits)
            assertEquals(2, Gabriel!!.findOne(0)!!.vecesAlBate)
            assertEquals(3, Gabriel!!.findOne(0)!!.doubles)
            assertEquals(4, Gabriel!!.findOne(0)!!.triples)
            assertEquals(5, Gabriel!!.findOne(0)!!.homeRuns)
            assertEquals(6, Gabriel!!.findOne(0)!!.runs)
            assertEquals(7, Gabriel!!.findOne(0)!!.strikeOut)
            assertEquals(8, Gabriel!!.findOne(0)!!.walks)
        }
    }
    @Nested
    inner class DeleteStats {

        @Test
        fun `deleting a Stat that does not exist, returns false`() {

            assertEquals(false, Jorge!!.delete(-1))
            assertEquals(false, Jorge!!.delete(5))
        }

        @Test
        fun `deleting a Stats that exists deletes and returns deleted object`() {
            assertEquals(1, Jorge!!.statSize())
            assertEquals(true, Jorge!!.delete(0))
            assertEquals(0, Jorge!!.statSize())

        }
    }



}