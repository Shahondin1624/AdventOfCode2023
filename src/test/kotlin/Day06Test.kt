import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test06 {
    private val func = Day06()
    private val input = splitToList(
        "Time:      7  15   30\n" +
                "Distance:  9  40  200"
    )

    @Test
    fun testIntro1() {
        val result = func.part1(input)
        val expected = 288L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day06_1")
        val result = func.part1(lines)
        val expected = 1660968L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val result = func.part2(input)
        val expected = 71503L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day06_1")
        val result = func.part2(lines)
        val expected = 26499773L
        Assertions.assertEquals(expected, result)
    }
}