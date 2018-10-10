package parser.data

import com.google.gson.annotations.SerializedName

data class Building(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("buildingId") var buildingId: Int? = null,
        @SerializedName("number") var numbers: List<Int>? = null,
        @SerializedName("tag") var tag: String = "",
        @SerializedName("name") var name: String = "",
        @SerializedName("coordinates") var coords: List<Coord>? = null)