import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Test05 {
    private val func = Day05()
    private val input = splitToList(
        "seeds: 79 14 55 13\n" +
                "\n" +
                "seed-to-soil map:\n" +
                "50 98 2\n" +
                "52 50 48\n" +
                "\n" +
                "soil-to-fertilizer map:\n" +
                "0 15 37\n" +
                "37 52 2\n" +
                "39 0 15\n" +
                "\n" +
                "fertilizer-to-water map:\n" +
                "49 53 8\n" +
                "0 11 42\n" +
                "42 0 7\n" +
                "57 7 4\n" +
                "\n" +
                "water-to-light map:\n" +
                "88 18 7\n" +
                "18 25 70\n" +
                "\n" +
                "light-to-temperature map:\n" +
                "45 77 23\n" +
                "81 45 19\n" +
                "68 64 13\n" +
                "\n" +
                "temperature-to-humidity map:\n" +
                "0 69 1\n" +
                "1 0 69\n" +
                "\n" +
                "humidity-to-location map:\n" +
                "60 56 37\n" +
                "56 93 4"
    )

    @Test
    fun testIntro1() {
        val result = func.part1(input)
        val expected = 35L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day05_1")
        val result = func.part1(lines)
        val expected = 389056265L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val result = func.part2(input)
        val expected = 46L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day05_1")
        val result = func.part2(lines)
        val expected = 46L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testTraverseOrigin() {
        val originRange: LongRange = 0L..5
        val destinationRange: LongRange = 3L..8
        val traversal = traverseMapping(originRange, destinationRange, 2)
        Assertions.assertEquals(5, traversal)
    }

    @Test
    fun testTraverseOriginBackwards() {
        val originRange: LongRange = 0L..5
        val destinationRange: LongRange = 3L..8
        val traversal = reverseMapping(originRange, destinationRange, 5)
        Assertions.assertEquals(2, traversal)
    }
}