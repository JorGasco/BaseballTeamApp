import controllers.PlayersAPI
import models.Player
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertEquals
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
}