import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test04 {
    private val func = Day04()

    @Test
    fun testIntro1() {
        val input = splitToList(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                    "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                    "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                    "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                    "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                    "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
        )
        val result = func.part1(input)
        val expected = 13
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart1() {
        val lines = readInput("Day04_1")
        val result = func.part1(lines)
        val expected = 20667
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testIntro2() {
        val lines = splitToList(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                    "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                    "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                    "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                    "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                    "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
        )
        val result = func.part2(lines)
        val expected = 30
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun testPart2() {
        val lines = readInput("Day04_1")
        val result = func.part2(lines)
        val expected = 5833065
        Assertions.assertEquals(expected, result)
    }
}