package parser.data

import com.google.gson.annotations.SerializedName

data class Coord(
        @SerializedName("longitude") var longitude: String = "",
        @SerializedName("latitude") var latitude: String = "")