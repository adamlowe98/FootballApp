package data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import exists
import org.jetbrains.anko.AnkoLogger
import read
import write
import java.util.*


val JSON_FILE = "players.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PlayerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}


 class PlayerJSONStore : PlayerStore, AnkoLogger {
     val context: Context
     var players = mutableListOf<PlayerModel>()

     constructor (context: Context) {
         this.context = context
         if (exists(context, JSON_FILE)) {
             deserialize()
         }
     }

     override fun findAll(): MutableList<PlayerModel> {
         return players
     }

     override fun create(player: PlayerModel) {
         player.id = generateRandomId()
         players.add(player)
         serialize()
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
             foundPlayer.lat = player.lat
             foundPlayer.lng = player.lng
             foundPlayer.zoom = player.zoom
         }
         serialize()
     }

     override fun delete(player: PlayerModel) {
         players.remove(player)
         serialize()
     }

     private fun serialize() {
         val jsonString = gsonBuilder.toJson(players, listType)
         write(context, JSON_FILE, jsonString)
     }

     private fun deserialize() {
         val jsonString = read(context, JSON_FILE)
         players = Gson().fromJson(jsonString, listType)
     }
 }


