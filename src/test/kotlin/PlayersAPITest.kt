import controllers.PlayersAPI
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

class PlayersAPITest {
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

    @Test
    fun `adding a Player to a populated list adds to ArrayList`() {
        val newPlayer = Player(0, "New", "Player", 25, 25.00, 25.00, "outfield", false)

        assertEquals(5, populatedPlayers!!.numberOfPlayers())
        assertTrue(populatedPlayers!!.add(newPlayer))
        assertEquals(6, populatedPlayers!!.numberOfPlayers())
        assertEquals(newPlayer, populatedPlayers!!.findPlayer(populatedPlayers!!.numberOfPlayers() - 1))
    }

    @Test
    fun `adding a Player to an empty list adds to ArrayList`() {
        val newPlayer = Player(0, "New", "Player", 25, 25.00, 25.00, "outfield", false)


        assertEquals(0, emptyPlayers!!.numberOfPlayers())
        assertTrue(emptyPlayers!!.add(newPlayer))
        assertEquals(1, emptyPlayers!!.numberOfPlayers())
        assertEquals(newPlayer, emptyPlayers!!.findPlayer(emptyPlayers!!.numberOfPlayers() - 1))
    }

    @Nested
    inner class UpdatePlayers {
        @Test
        fun `updating a Player that does not exist returns false`() {
            assertFalse(
                populatedPlayers!!.update(
                    6,
                    Player(6, "Updating Name", "Updating Surname", 50, 3.00, 50.00, "Pitcher")
                )
            )
            assertFalse(
                populatedPlayers!!.update(
                    -1,
                    Player(-1, "Updating Name", "Updating Surname", 50, 3.00, 50.00, "Pitcher")
                )
            )
            assertFalse(
                emptyPlayers!!.update(
                    0,
                    Player(0, "Updating Name", "Updating Surname", 50, 3.00, 50.00, "Pitcher")
                )
            )
        }

        @Test
        fun `updating a Player that exists returns true and updates`() {
            // check Player 5 exists and check the contents
            assertEquals(Jorge, populatedPlayers!!.findPlayer(0))
            assertEquals("Jorge", populatedPlayers!!.findPlayer(0)!!.playerName)
            assertEquals("Gasco", populatedPlayers!!.findPlayer(0)!!.playerSurname)
            assertEquals(25, populatedPlayers!!.findPlayer(0)!!.age)
            assertEquals(25.00, populatedPlayers!!.findPlayer(0)!!.height)
            assertEquals("outfield", populatedPlayers!!.findPlayer(0)!!.position)

            // update Player 5 with new information and ensure contents updated successfully
            assertTrue(populatedPlayers!!.update(0, Player(0, "Updated", "Updated", 25, 25.00, 25.00, "outfield")))
            assertEquals("Updated", populatedPlayers!!.findPlayer(0)!!.playerName)
            assertEquals("Updated", populatedPlayers!!.findPlayer(0)!!.playerSurname)
            assertEquals(25, populatedPlayers!!.findPlayer(0)!!.age)
            assertEquals(25.00, populatedPlayers!!.findPlayer(0)!!.height)
            assertEquals(25.00, populatedPlayers!!.findPlayer(0)!!.weight)
            assertEquals("outfield", populatedPlayers!!.findPlayer(0)!!.position)
        }
    }

    @Nested
    inner class DeletePlayers {

        @Test
        fun `deleting a Player that does not exist, returns false`() {
            assertEquals(false, emptyPlayers!!.delete(0))
            assertEquals(false, populatedPlayers!!.delete(-1))
            assertEquals(false, populatedPlayers!!.delete(5))
        }

        @Test
        fun `deleting a Player that exists delete and returns deleted object`() {
            assertEquals(5, populatedPlayers!!.numberOfPlayers())
            assertEquals(true, populatedPlayers!!.delete(4))
            assertEquals(4, populatedPlayers!!.numberOfPlayers())
            assertEquals(true, populatedPlayers!!.delete(0))
            assertEquals(3, populatedPlayers!!.numberOfPlayers())
        }
    }

    @Nested
    inner class ListPlayers {

        @Test
        fun `listAllPlayers returns No Players Stored message when ArrayList is empty`() {
            assertEquals(0, emptyPlayers!!.numberOfPlayers())
            assertTrue(emptyPlayers!!.listAllPLayers().lowercase().contains("no players"))
        }

        @Test
        fun `listAllPlayers returns Players when ArrayList has Players stored`() {
            assertEquals(5, populatedPlayers!!.numberOfPlayers())
            val playersString = populatedPlayers!!.listAllPLayers().lowercase()
            assertTrue(playersString.contains("jorge"))
            assertTrue(playersString.contains("patty"))
            assertTrue(playersString.contains("diego"))
            assertTrue(playersString.contains("dani"))
            assertTrue(playersString.contains("gabriel"))
        }
    }
//Stats

    @Nested
    inner class AddStat {
        @Test
        fun `adding a Stat to a Player in populated list adds to set`() {
            val newStat = Stat(8,7,6,5,4,3,2,1,0)
            assertEquals(5, populatedPlayers!!.numberOfStats())
            assertTrue(Jorge!!.addStat(newStat))
            assertEquals(6, populatedPlayers!!.numberOfStats())
            assertEquals(newStat, Jorge!!.findOne(Jorge!!.statSize() - 1))
        }
    }
@Nested
inner class UpdateStats {
    @Test
    fun `updating a Stat that exists returns true and updates`() {
        // check franchise 5 exists and check the games
        assertEquals(Gabriel, populatedPlayers!!.findPlayer(4))
        assertEquals(1, Gabriel!!.findOne(0)!!.vecesAlBate)
        assertEquals(2, Gabriel!!.findOne(0)!!.hits)
        assertEquals(3, Gabriel!!.findOne(0)!!.doubles)
        assertEquals(4, Gabriel!!.findOne(0)!!.triples)
        assertEquals(5, Gabriel!!.findOne(0)!!.homeRuns)
        assertEquals(6, Gabriel!!.findOne(0)!!.runs)
        assertEquals(7, Gabriel!!.findOne(0)!!.strikeOut)
        assertEquals(8, Gabriel!!.findOne(0)!!.walks)

        // update franchise 5 with new game information and ensure contents updated successfully
        assertTrue(Gabriel!!.update(0, Stat(2, 3, 4, 5, 6, 7, 8, 9)))
        assertEquals(2, Gabriel!!.findOne(0)!!.vecesAlBate)
        assertEquals(3, Gabriel!!.findOne(0)!!.hits)
        assertEquals(4, Gabriel!!.findOne(0)!!.doubles)
        assertEquals(5, Gabriel!!.findOne(0)!!.triples)
        assertEquals(6, Gabriel!!.findOne(0)!!.homeRuns)
        assertEquals(7, Gabriel!!.findOne(0)!!.runs)
        assertEquals(8, Gabriel!!.findOne(0)!!.strikeOut)
        assertEquals(9, Gabriel!!.findOne(0)!!.walks)
    }
}

    // PERSISTENCE
    @Nested
    inner class PersistenceTests {
        @Test
        fun `saving and loading an empty collection in JSON doesn't crash app`() {
            val storingPlayers = PlayersAPI(JSONSerializer(File("players.json")))
            storingPlayers.save()

            val loadedPlayers = PlayersAPI(JSONSerializer(File("players.json")))
            loadedPlayers.load()

            assertEquals(0, storingPlayers.numberOfPlayers())
            assertEquals(0, loadedPlayers.numberOfPlayers())
            assertEquals(storingPlayers.numberOfPlayers(), loadedPlayers.numberOfPlayers())
        }

        @Test
        fun `saving and loading an loaded collection in JSON doesn't loose data`() {
            val storingPlayers = PlayersAPI(JSONSerializer(File("players.json")))
            storingPlayers.add(Jorge!!)
            storingPlayers.add(Patty!!)
            storingPlayers.add(Diego!!)
            storingPlayers.save()

            val loadedPlayers = PlayersAPI(JSONSerializer(File("players.json")))
            loadedPlayers.load()

            assertEquals(3, storingPlayers.numberOfPlayers())
            assertEquals(3, loadedPlayers.numberOfPlayers())
            assertEquals(storingPlayers.numberOfPlayers(), loadedPlayers.numberOfPlayers())
            assertEquals(storingPlayers.findPlayer(0), loadedPlayers.findPlayer(0))
            assertEquals(storingPlayers.findPlayer(1), loadedPlayers.findPlayer(1))
            assertEquals(storingPlayers.findPlayer(2), loadedPlayers.findPlayer(2))
        }

    }
}

