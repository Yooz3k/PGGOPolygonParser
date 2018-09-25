package parser.data

data class Building(
        var number: List<Int>? = null,
        var name: String = "",
        var coords: List<Coord>? = null)