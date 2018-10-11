package parser.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Coord(
        @SerializedName("longitude") var longitude: BigDecimal = BigDecimal.ZERO,
        @SerializedName("latitude") var latitude: BigDecimal = BigDecimal.ZERO)