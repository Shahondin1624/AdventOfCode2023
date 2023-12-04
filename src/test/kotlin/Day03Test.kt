import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test03 {
    private val func = Day03()

    @Test
    fun testIntro1() {
        val input = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...\$.*....",
            ".664.598.."
        )
        val result = func.part1(input)
        val expected = 4361
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day03_1")
        val result = func.part1(lines)
        val expected = 2600
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val lines = listOf(""
        )
        val result = func.part2(lines)
        val expected = 2286
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day03_1")
        val result = func.part2(lines)
        val expected = 86036
        Assertions.assertEquals(expected, result)
    }
}