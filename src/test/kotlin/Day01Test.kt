import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test {
    private val func = Day01()

    @Test
    fun testIntro1() {
        val input = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
        )
        val result = func.part1(input)
        val expected = 142
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day01_1")
        val result = func.part1(lines)
        val expected = 53194
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val lines = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
        )
        val result = func.part2(lines)
        val expected = 281
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day01_1")
        val result = func.part2(lines)
        val expected = 54249
        Assertions.assertEquals(expected, result)
    }
}