package data

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlayerMemStore : PlayerStore, AnkoLogger {
     fun findById(id: Long): PlayerModel? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val players = ArrayList<PlayerModel>()

    override fun findAll(): List<PlayerModel> {
        return players
    }

    override fun create(player: PlayerModel) {
        player.id = getId()
        players.add(player)
        logAll()
    }

    override fun update(player: PlayerModel) {
        val playersList = findAll() as ArrayList<PlayerModel>
        var foundPlayer: PlayerModel? = playersList.find { p -> p.id == player.id }
        if (foundPlayer != null) {
            foundPlayer.title = player.title
            foundPlayer.Age = player.Age
            foundPlayer.team = player.team
            foundPlayer.cost = player.cost
            foundPlayer.Pos = player.Pos
            foundPlayer.League = player.League
            foundPlayer.image = player.image
            logAll()
        }
    }

    override fun delete(player: PlayerModel) {
        players.remove(player)
    }


    fun logAll() {
        players.forEach{ info("${it}") }
    }


}