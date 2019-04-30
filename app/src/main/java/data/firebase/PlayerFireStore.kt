package data.firebase



import android.content.Context
import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import data.PlayerModel
import data.PlayerStore
import helpers.readImageFromPath
import org.jetbrains.anko.AnkoLogger

import java.io.ByteArrayOutputStream
import java.io.File

class PlayerFireStore(val context: Context) : PlayerStore, AnkoLogger {

    val players = ArrayList<PlayerModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference
    lateinit var st: StorageReference

    suspend override fun findAll(): List<PlayerModel> {
        return players
    }

    suspend override fun findById(id: Long): PlayerModel? {
        val foundPlayer: PlayerModel? = players.find { p -> p.id == id }
        return foundPlayer
    }

    suspend override fun create(player: PlayerModel) {
        val key = db.child("users").child(userId).child("players").push().key
        key?.let {
            player.fbId = key
            players.add(player)
            db.child("users").child(userId).child("players").child(key).setValue(player)
            updateImage(player)
        }
    }

    suspend override fun update(player: PlayerModel) {
        var foundPlayer: PlayerModel? = player.find { p -> p.fbId == player.fbId }
        if (foundPlayer != null) {
            foundPlayer.title = player.title
            foundPlayer.team = player.team
            foundPlayer.cost = player.cost
            foundPlayer.Pos = player.Pos
            foundPlayer.image = player.image
            foundPlayer.location = player.location
        }

        db.child("users").child(userId).child("players").child(player.fbId).setValue(player)
        if ((player.image.length) > 0 && (player.image[0] != 'h')) {
            updateImage(player)
        }
    }

    suspend override fun delete(placemark: PlayerModel) {
        db.child("users").child(userId).child("placemarks").child(placemark.fbId).removeValue()
        players.remove(placemark)
    }

    fun updateImage(player: PlayerModel) {
        if (player.image != "") {
            val fileName = File(player.image)
            val imageName = fileName.getName()

            var imageRef = st.child(userId + '/' + imageName)
            val baos = ByteArrayOutputStream()
            val bitmap = readImageFromPath(context, player.image)

            bitmap?.let {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val uploadTask = imageRef.putBytes(data)
                uploadTask.addOnFailureListener {
                    println(it.message)
                }.addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                        player.image = it.toString()
                        db.child("users").child(userId).child("placemarks").child(player.fbId).setValue(player)
                    }
                }
            }
        }
    }

    fun fetchPlayers(playersReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot!!.children.mapNotNullTo(players) { it.getValue<PlayerModel>(PlayerModel::class.java) }
                playersReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        st = FirebaseStorage.getInstance().reference
        players.clear()
        db.child("users").child(userId).child("players").addListenerForSingleValueEvent(valueEventListener)
    }
}