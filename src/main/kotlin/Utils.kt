import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Matcher
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/test/resources/$name").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun splitToList(string: String): List<String> {
    return string.split("\n").toList()
}

fun getFileName(day: Int): String {
    return String.format("Day%02d_1", day)
}

fun Matcher.collectMatchingGroups(): List<String> {
    val list: MutableList<String> = mutableListOf()
    while (this.find()) {
        list.add(this.group())
    }
    return list
}

fun lcm(val1: Long, val2: Long) = val1 / gcd(val1, val2) * val2

fun lcm(numbers: List<Long>): Long {
    if (numbers.size < 2) throw IllegalArgumentException("Must at least have two numbers to calculate lcm")
    var num1 = numbers[0]
    var num2 = numbers[1]
    for (i in 2..numbers.size) {
        num1 = lcm(num1, num2)
        num2 = numbers.getOrElse(i) { -1L }
    }
    return num1
}

fun gcd(val1: Long, val2: Long): Long {
    var num1 = val1
    var num2 = val2
    while (num2 != 0L) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

inline fun <reified T: Number> String.toNum(noinline converterFunction: (String) -> T = { it.toLong() as T }): List<T> {
    val pattern = "([+-]?\\d+\\.?\\d*\\b)".toRegex().toPattern()
    val matcher = pattern.matcher(this)
    return matcher.collectMatchingGroups().map(converterFunction)
}

inline fun <reified T> List<String>.parseLines(noinline lineParser: (String) -> T = { it.toLong() as T }): List<T> {
    return this.map { lineParser.invoke(it) }
}
