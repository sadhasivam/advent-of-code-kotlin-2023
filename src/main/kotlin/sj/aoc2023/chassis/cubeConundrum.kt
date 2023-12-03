package sj.aoc2023.chassis

enum class CubeColor(val colorName: String) {
    Red ("red"),
    Green("Green"),
    Blue("Blue");

    companion object {
        fun fromString(colorName: String): CubeColor {
            return entries.firstOrNull { it.colorName.equals(colorName, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid color name: $colorName")
        }
    }
}

data class ColorCount(val count: Int, val color: CubeColor)

typealias  GameRun = List<ColorCount>

data class Game(val id: Int, val gameRun: List<GameRun>)

fun String.toColorCount(): ColorCount {
    val parts = this.trim().split(" ")
    require(parts.size == 2 && parts[0].toIntOrNull() != null) {
        "String must be in the format '<Int> <String>'"
    }
    val color = CubeColor.fromString(parts[1])
    return ColorCount(parts[0].toInt(), color)
}

fun Game.isPossible(vararg conditions: ColorCount): Boolean {
    return !this.gameRun.any { run ->
        run.any { cc ->
            conditions.any { condition ->
                cc.color == condition.color && cc.count > condition.count
            }
        }
    }
}

fun Game.powerFewestCubesOfEachColor(): Int  {
    val flatList = this.gameRun.flatten()
    val maxColorMap = CubeColor.entries.associateWith { color ->
        flatList.filter { it.color == color }.maxOfOrNull { it.count } ?: 0
    }
    return maxColorMap.values.fold(1) { acc, count -> acc * count }
}

fun parseGameRecord(record: String): Game {
    val parts = record.split(":")
    val gameId = parts.first().split(" ").last().toIntOrNull() ?: 0
    val runs = parts.last().split(";")
    return Game(gameId,   runs.map {
        it.split(",").map { cc ->
            cc.toColorCount()
        }
    })
}

fun sumPossibleGameIds(games: List<Game>, vararg conditions: ColorCount): Int {
   return games.sumOf { if (it.isPossible(*conditions)) it.id else 0 }
}
