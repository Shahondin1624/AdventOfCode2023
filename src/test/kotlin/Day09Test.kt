import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day09Test {
    private val func = Day09()
    private val day = javaClass.simpleName.filter { it.isDigit() }.toInt()
    private val input = splitToList(
        "0 3 6 9 12 15\n" +
                "1 3 6 10 15 21\n" +
                "10 13 16 21 30 45"
    )

    @Test
    fun testIntro1() {
        val result = func.part1(input)
        val expected: Long = 114L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput(getFileName(day))
        val result = func.part1(lines)
        val expected: Long = 1930746032L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val result = func.part2(input)
        val expected: Long = 2L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput(getFileName(day))
        val result = func.part2(lines)
        val expected: Long = 1154L
        Assertions.assertEquals(expected, result)
    }
}