import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import java.util.*
import java.util.regex.Matcher
import kotlin.system.measureTimeMillis

class Day05 {
    private val logger = KotlinLogging.logger {}

    fun part1(input: List<String>): Long {
        val data = Input.parse(input)
        val array = data.asArray()
        val locations: MutableList<Long> = mutableListOf()
        for (seed in data.seeds) {
            calculateForSeed(seed, array, locations)
        }
        return locations.min()
    }


    fun part2(input: List<String>): Long = runBlocking {
        val data = Input.parse(input)
        val array = data.asArray()
        val locations: MutableList<Long> = mutableListOf()
        val seedRanges = createRanges(data.seeds)
        for ((counter, seedRange) in seedRanges.withIndex()) {
            logger.debug { "Processing ${counter + 1}/${seedRanges.size}" }
            val time = measureTimeMillis {
                val deferred = seedRange.map { seed ->
                    async(Dispatchers.Default) {
                        calculateForSeed(seed, array, locations)
                    }
                }
                deferred.forEach { it.await() }
            }
            logger.debug { "Processing ${counter + 1}/${seedRanges.size} took $time ms" }
        }
        return@runBlocking locations.min() ?: 0
    }

    private fun calculateForSeed(
        seed: Long,
        array: Array<List<Mapping>>,
        locations: MutableList<Long>
    ) {
        var location = seed
        for (i in 0..6) {
            val (destinationRange, sourceRange, _) = findRange(array[i], location) ?: continue
            val traversal = traverseMapping(sourceRange, destinationRange, location)
            location = traversal
        }
        locations.add(location)
    }

    private fun createRanges(seedRanges: List<Long>): List<LongRange> {
        return seedRanges.chunked(2) { (start, end) -> start..start + end }
    }

}

data class Mapping(val destinationRange: LongRange, val sourceRange: LongRange, val range: Long)


data class Input(
    val seeds: List<Long>,
    val seedsToSoil: List<Mapping>,
    val soilToFertilizer: List<Mapping>,
    val fertilizerToWater: List<Mapping>,
    val waterToLight: List<Mapping>,
    val lightToTemperature: List<Mapping>,
    val temperatureToHumidity: List<Mapping>,
    val humidityToLocation: List<Mapping>
) {
    fun asArray(): Array<List<Mapping>> {
        return arrayOf(
            seedsToSoil,
            soilToFertilizer,
            fertilizerToWater,
            waterToLight,
            lightToTemperature,
            temperatureToHumidity,
            humidityToLocation
        )
    }

    companion object Parser {
        fun parse(input: List<String>): Input {
            val seeds: MutableList<Long> = mutableListOf()
            val seedsToSoil: MutableList<Mapping> = mutableListOf()
            val soilToFertilizer: MutableList<Mapping> = mutableListOf()
            val fertilizerToWater: MutableList<Mapping> = mutableListOf()
            val waterToLight: MutableList<Mapping> = mutableListOf()
            val lightToTemperature: MutableList<Mapping> = mutableListOf()
            val temperatureToHumidity: MutableList<Mapping> = mutableListOf()
            val humidityToLocation: MutableList<Mapping> = mutableListOf()
            val wrapper = arrayOf(
                seedsToSoil,
                soilToFertilizer,
                fertilizerToWater,
                waterToLight,
                lightToTemperature,
                temperatureToHumidity,
                humidityToLocation
            )
            var index = -1
            val wordCharacter = "[a-z-:]".toRegex()
            val wordPattern = wordCharacter.toPattern()
            val digitPattern = "\\d+".toRegex().toPattern()
            seeds.addAll(extractSeed(digitPattern.matcher(input[0])))
            for (i in 1..<input.size) {
                val line = input[i]
                if (wordPattern.matcher(line).find()) {
                    index++
                    continue
                }
                if (line.isBlank()) {
                    continue
                }
                wrapper[index].add(parseMappingsLine(line))
            }
            return Input(
                seeds,
                seedsToSoil,
                soilToFertilizer,
                fertilizerToWater,
                waterToLight,
                lightToTemperature,
                temperatureToHumidity,
                humidityToLocation
            )
        }

        private fun parseMappingsLine(line: String): Mapping {
            val whitespace = "\\D+".toRegex()
            val nums = line.split(whitespace).toLongs()
            return createMapping(nums)
        }

        private fun extractSeed(matcher: Matcher): List<Long> {
            val numbers: MutableList<Long> = mutableListOf()
            while (matcher.find()) {
                val num = matcher.group()!!
                numbers.add(num.toLong())
            }
            return numbers
        }

        private fun createMapping(list: List<Long>): Mapping {
            val destinationStart = list[0]
            val destinationRange = destinationStart..destinationStart + list[2]
            val sourceStart = list[1]
            val sourceRange = sourceStart..sourceStart + list[2]
            return Mapping(destinationRange, sourceRange, list[2])
        }

        private fun List<String>.toLongs(): List<Long> {
            return this.map { it.toLong() }.toList()
        }

    }
}

private fun findRange(mappings: List<Mapping>, number: Long): Mapping? {
    return mappings.firstOrNull { it.sourceRange.contains(number) }
}

fun traverseMapping(origin: LongRange, destination: LongRange, inOrigin: Long): Long {
    val relativePosition = inOrigin - origin.first
    return destination.first + relativePosition
}

fun reverseMapping(origin: LongRange, destinationRange: LongRange, inDestination: Long): Long {
    val relativePosition = inDestination - destinationRange.first
    return origin.first + relativePosition
}


