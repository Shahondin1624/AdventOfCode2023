import mu.KotlinLogging
import java.util.regex.Pattern

class Day03 {
    private val logger = KotlinLogging.logger {}


    fun part1(input: List<String>): Int {
        val data = transformInput(input)
        val specials =
            data.flatMap { arr -> arr.filter { char -> char.isLetterOrDigit().not() && char != '.' } }.toSet()
        val numberToIndex: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()
        val pattern = Pattern.compile("\\d+")!!
        for ((x, chars) in data.withIndex()) {
            val matcher = pattern.matcher(chars.concatToString())
            while (matcher.find()) {
                val number = matcher.group().toInt()
                val index = matcher.start()
                numberToIndex[number] = Pair(x, index)
            }
        }
        return numberToIndex.entries.filter { entry -> adjacentContains(data, entry.value, specials) }
            .map { entry -> entry.key }
            .fold(0) { prev, value -> prev + value }
    }

    fun part2(input: List<String>): Int {
        return 0
    }
}

private fun transformInput(input: List<String>): Array<CharArray> {
    return input.map { it.toCharArray() }.toTypedArray()
}

private fun adjacentContains(input: Array<CharArray>, startIndex: Pair<Int, Int>, chars: Set<Char>): Boolean {
    val adjacent = calculateAdjacentIndices(input, startIndex)
    return adjacent.map { index -> input[index.first][index.second] }
        .any { char -> chars.contains(char) }
}

private fun calculateAdjacentIndices(input: Array<CharArray>, startIndex: Pair<Int, Int>): List<Pair<Int, Int>> {
    val arr = input[startIndex.first]
    var end: Pair<Int, Int> = Pair(startIndex.first, startIndex.second)
    for (i in startIndex.second..<arr.size) {
        if (arr[i].isDigit().not()) {
            end = Pair(startIndex.first, i)
            break
        }
    }
    val indices: MutableSet<Pair<Int, Int>> = mutableSetOf()
    for (i in startIndex.second..<end.second) {
        val index = Pair(startIndex.first, i)
        indices.addAll(calculateAdjacentIndices(index))
    }
    return indices.filter { ind -> isValidPosition(input, ind) }
        .filter { ind -> between(startIndex, end, ind).not() }
        .toList()
}

private fun calculateAdjacentIndices(index: Pair<Int, Int>): Set<Pair<Int, Int>> {
    return setOf(
        Pair(index.first - 1, index.second - 1),
        Pair(index.first, index.second - 1),
        Pair(index.first + 1, index.second - 1),
        Pair(index.first - 1, index.second),
        Pair(index.first + 1, index.second),
        Pair(index.first - 1, index.second + 1),
        Pair(index.first, index.second + 1),
        Pair(index.first + 1, index.second + 1)
    )
}

//Only for one line
private fun between(start: Pair<Int, Int>, end: Pair<Int, Int>, index: Pair<Int, Int>): Boolean {
    return start.first <= end.first && index.first >= start.first && index.first <= end.first
}

private fun isValidPosition(input: Array<CharArray>, index: Pair<Int, Int>): Boolean {
    if (index.first < 0 || index.first >= input.size) return false
    if (index.second < 0 || index.second >= input[index.first].size) return false
    return true
}




