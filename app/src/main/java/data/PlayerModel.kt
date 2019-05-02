package data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class PlayerModel( var id: Long = 0, var title: String = "", var Age: String = "", var team: String = "", var cost: String = "", var Pos: String = "", var League: String = "", var image: String = "", var lat : Double = 0.0,
                       var lng: Double = 0.0,
                       var zoom: Float = 0f, var location : Location = Location()): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable