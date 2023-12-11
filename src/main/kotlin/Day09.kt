import mu.KotlinLogging

class Day09 {
    private val logger = KotlinLogging.logger {}

    fun part1(input: List<String>): Long {
        return Day09Part1().part1(input)
    }

    fun part2(input: List<String>): Long {
        return Day09Part2().part2(input)
    }

    class Day09Common {
        data class History(val numbers: List<Long>) {
            companion object Parser {
                fun parseLine(line: String): History {
                    val historyData: List<Long> = line.toNum()
                    return History(historyData)
                }

                fun parseLines(lines: List<String>): List<History> {
                    return lines.parseLines { line -> line.toNum() { it.toLong() } }.map { History(it) }
                }

            }
        }

        fun solve(list: List<Long>): Long {
            return if (list.all { it == 0L }) 0 else {
                val differences = list.windowed(2, 1).map { it[1] - it[0] }
                list.last() + solve(differences)
            }
        }
    }

    private class Day09Part1 {
        fun part1(input: List<String>): Long {
            val day9 = Day09Common()
            val histories = Day09Common.History.parseLines(input)
            return histories.sumOf { day9.solve(it.numbers) }
        }
    }

    private class Day09Part2 {
        fun part2(input: List<String>): Long {
            val day9 = Day09Common()
            val histories = Day09Common.History.parseLines(input)
            return histories.map { it.numbers.reversed() }.sumOf { day9.solve(it) }
        }
    }
}



