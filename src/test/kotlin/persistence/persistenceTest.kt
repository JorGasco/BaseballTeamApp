package persistence

import controllers.PlayersAPI
import models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class persistenceTest {

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