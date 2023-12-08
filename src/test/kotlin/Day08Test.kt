import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day08Test {
    private val func = Day08()
    private val day = javaClass.simpleName.filter { it.isDigit() }.toInt()
    private val input = splitToList(
        "RL\n" +
                "\n" +
                "AAA = (BBB, CCC)\n" +
                "BBB = (DDD, EEE)\n" +
                "CCC = (ZZZ, GGG)\n" +
                "DDD = (DDD, DDD)\n" +
                "EEE = (EEE, EEE)\n" +
                "GGG = (GGG, GGG)\n" +
                "ZZZ = (ZZZ, ZZZ)"
    )

    @Test
    fun testIntro1() {
        val result = func.part1(input)
        val expected: Long = 2
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput(getFileName(day))
        val result = func.part1(lines)
        val expected: Long = 19241
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val specificInput = splitToList(
            "LR\n" +
                    "\n" +
                    "11A = (11B, XXX)\n" +
                    "11B = (XXX, 11Z)\n" +
                    "11Z = (11B, XXX)\n" +
                    "22A = (22B, XXX)\n" +
                    "22B = (22C, 22C)\n" +
                    "22C = (22Z, 22Z)\n" +
                    "22Z = (22B, 22B)\n" +
                    "XXX = (XXX, XXX)"
        )
        val result = func.part2(specificInput)
        val expected: Long = 6
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput(getFileName(day))
        val result = func.part2(lines)
        val expected: Long = 9606140307013L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testLcm() {
        val num1 = 3L
        val num2 = 2L
        val result = lcm(num1, num2)
        val expected = 6L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testLcmList() {
        val numbers = listOf(2L, 3L, 4L, 5L)
        val result = lcm(numbers)
        val expected = 60L
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testGcd() {
        val num1 = 12L
        val num2 = 14L
        val result = gcd(num1, num2)
        val expected = 2L
        Assertions.assertEquals(expected, result)
    }
}