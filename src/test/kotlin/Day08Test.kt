import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day08Test {
    private val func = Template()
    private val day = javaClass.simpleName.filter { it.isDigit() }.toInt()
    private val input = splitToList(
        ""
    )

    @Test
    fun testIntro1() {
        val result = func.part1(input)
        val expected: Long = 0
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput(getFileName(day))
        val result = func.part1(lines)
        val expected: Long = 0
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val result = func.part2(input)
        val expected: Long = 0
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput(getFileName(day))
        val result = func.part2(lines)
        val expected: Long = 0
        Assertions.assertEquals(expected, result)
    }
}