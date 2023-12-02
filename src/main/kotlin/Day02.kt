import mu.KotlinLogging

class Day02 {
    private val logger = KotlinLogging.logger {}
    private val red = 12
    private val green = 13
    private val blue = 14

    fun part1(input: List<String>): Int {
        return input.map { line -> Parser.parseLine(line) }
            .filter { game -> isGamePossible(game, red, green, blue) }
            .fold(0) { previous, game -> previous + game.id }
    }

    fun part2(input: List<String>): Int {
        return input.map { line -> Parser.parseLine(line) }
            .map { game -> powerOfGame(game) }
            .fold(0) { prev, value -> prev + value }
    }
}

private fun powerOfGame(game: Game): Int {
    var red = 0
    var green = 0
    var blue = 0
    for (subset in game.subsets) {
        if (subset.red > red) {
            red = subset.red
        }
        if (subset.green > green) {
            green = subset.green
        }
        if (subset.blue > blue) {
            blue = subset.blue
        }
    }
    return red * green * blue
}

private fun isGamePossible(game: Game, red: Int, green: Int, blue: Int): Boolean {
    for (subset in game.subsets) {
        if (subset.red > red) return false
        if (subset.green > green) return false
        if (subset.blue > blue) return false
    }
    return true
}

class Parser {
    companion object Parser {
        fun parseLine(line: String): Game {
            val noWhitespaces = line.filterNot { it.isWhitespace() }
            val game = noWhitespaces.substringBefore(":").removePrefix("Game").toInt()
            val components = noWhitespaces.substringAfter(":").split(";")
            val subsets = mutableListOf<Subset>()
            for (component in components) {
                val dieStrings = component.split(",")
                var red = 0
                var blue = 0
                var green = 0
                for (dieString in dieStrings) {
                    val colorStr = dieString.replace("\\d+".toRegex(), "")
                    val color = Color.parse(colorStr)
                    val amount = dieString.replace(color.name.lowercase(), "").toInt()
                    when (color) {
                        Color.RED -> red += amount
                        Color.BLUE -> blue += amount
                        Color.GREEN -> green += amount
                    }
                }
                subsets.add(Subset(red, green, blue))
            }
            return Game(game, subsets)
        }
    }
}

enum class Color {
    BLUE,
    RED,
    GREEN;

    companion object Parser {
        fun parse(string: String): Color {
            for (value in entries) {
                if (value.name.lowercase() == string) {
                    return value
                }
            }
            throw IllegalArgumentException("Could not parse $string, expected one of the following ${entries.toTypedArray()}")
        }
    }
}

data class Subset(val red: Int, val green: Int, val blue: Int)
data class Game(val id: Int, val subsets: List<Subset>)


