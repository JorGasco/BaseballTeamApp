package models
/*
import models.Stat
import controllers.PlayersAPI
import models.Player
import org.junit.jupiter.api.*
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertEquals

class StatTest {

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
    @Test
    fun `totalHits should calculate the sum of hits, doubles, triples, and home runs`() {

    val stat = Stat(1,2,3,4,5,6,7,8,9)
        Assertions.assertEquals(1, stat)

    }
}*/