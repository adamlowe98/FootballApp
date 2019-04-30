package data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PlayerModel(@PrimaryKey(autoGenerate = true) var id: Long = 0, var title: String = "", var team: String = "", var cost: String = "", var Pos: String = "", var image: String = "", var lat : Double = 0.0,
                       var lng: Double = 0.0,
                       var zoom: Float = 0f, @Embedded var location : Location = Location()): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable