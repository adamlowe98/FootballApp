package data

interface PlayerStore {
    suspend fun findAll(): List<PlayerModel>
    suspend fun create(player: PlayerModel)
    suspend fun update(player: PlayerModel)
    suspend fun delete(player: PlayerModel)
    suspend fun findById(id:Long) : PlayerModel?
    suspend fun logAll()
}