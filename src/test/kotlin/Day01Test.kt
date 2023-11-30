import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test {
    private val func = Day01()

    @Test
    fun testIntro() {
        val input = listOf("")
        val result = func.part1(input)
        val expected = 0 //TODO
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day01_1")
        val result = func.part1(lines)
        result.println()
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day01_2")
        val result = func.part2(lines)
        result.println()
    }
}