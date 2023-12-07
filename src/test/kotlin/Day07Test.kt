import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test07 {
    private val func = Day07()
    private val input = splitToList(
        "32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483"
    )

    @Test
    fun testIntro1() {
        val result = func.part1(input)
        val expected = 6440L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day07_1")
        val result = func.part1(lines)
        val expected = 248569531L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val result = func.part2(input)
        val expected = 5905L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day07_1")
        val result = func.part2(lines)
        val expected = 250382098L
        Assertions.assertEquals(expected, result)
    }
}