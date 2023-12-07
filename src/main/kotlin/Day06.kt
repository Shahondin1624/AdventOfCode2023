import mu.KotlinLogging

class Day06 {
    private val logger = KotlinLogging.logger {}

    fun part1(input: List<String>): Long {
        val races = Race.parse1(input)
        return races.map { getNumberOfPossibleWaysToWin(it) }
            .fold(1L) { previous, next -> previous * next }
    }


    fun part2(input: List<String>): Long {
        val preprocessed = input.map { it.replace("\\s+".toRegex(), "") }.toList()
        val race = Race.parse1(preprocessed)[0]
        var wins = 0L
        for (i in 1L..race.duration) {
            val distance = getDistance(i, race)
            if (isNewRecord(distance, race)) {
                wins++
            }
        }
        return wins
    }

}

data class Race(val duration: Long, val record: Long) {
    companion object Parser {
        fun parse1(input: List<String>): List<Race> {
            val number = "\\d+".toRegex().toPattern()
            val durations: MutableList<Long> = mutableListOf()
            val records: MutableList<Long> = mutableListOf()
            var matcher = number.matcher(input[0])
            while (matcher.find()) {
                durations.add(matcher.group().toLong())
            }
            matcher = number.matcher(input[1])
            while (matcher.find()) {
                records.add(matcher.group().toLong())
            }
            return durations.zip(records) { duration, record -> Race(duration, record) }.toList()
        }
    }
}

private fun getNumberOfPossibleWaysToWin(race: Race): Long {
    var wins = 0L
    for (i in 1 until race.duration) {
        val distance = getDistance(i, race)
        if (isNewRecord(distance, race)) {
            wins++
        }
    }
    return wins
}

private fun getDistance(waited: Long, race: Race): Long {
    val moved = waited * (race.duration - waited)
    return moved
}

private fun isNewRecord(distance: Long, race: Race): Boolean {
    return distance > race.record
}
