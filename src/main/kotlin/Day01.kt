import mu.KotlinLogging
import java.util.function.BiFunction
import java.util.function.Function
import java.util.regex.Pattern

class Day01 {
    private val logger = KotlinLogging.logger {}
    private val fold: (String, Int, Pattern, (String) -> Int, (Int, Int?) -> Int) -> Int =
        { line, previousValue, pattern, transformer, combiner ->
            val nextVal = runLine(
                line,
                pattern,
                transformer,
                combiner
            )
            val sum = previousValue + nextVal
            logger.debug { "$line, previousVal:$previousValue + nextVal:$nextVal -> $sum" }
            sum
        }
    private val combiner: (Int, Int?) -> Int =
        { firstDigit: Int, secondDigit: Int? -> "$firstDigit${secondDigit ?: firstDigit}".toInt() }

    fun part1(input: List<String>): Int {
        val pattern = Pattern.compile("\\d")
        val transformer = { captureGroup: String -> captureGroup.toInt() }
        return input.foldRight(0) { line, previousValue ->
            fold(line, previousValue, pattern, transformer, combiner)
        }
    }

    private fun runLine(
        line: String,
        pattern: Pattern,
        transformer: Function<String, Int>,
        combiner: BiFunction<Int, Int?, Int>
    ) =
        transformAndCombine(matcher(line, pattern), transformer, combiner)


    private fun matcher(line: String, pattern: Pattern): Pair<String, String?> {
        var lastNumber: String? = null
        val matcher = pattern.matcher(line)
        matcher.find()
        val firstNumber: String = matcher.group()!!
        while (matcher.find()) {
            lastNumber = matcher.group()
        }
        logger.debug { "$line, n1:$firstNumber, n2:$lastNumber" }
        return Pair(firstNumber, lastNumber)
    }

    private fun transformAndCombine(
        numbers: Pair<String, String?>,
        transformer: Function<String, Int>,
        combiner: BiFunction<Int, Int?, Int>
    ): Int {
        val firstDigit = transformer.apply(numbers.first)
        val secondDigit = numbers.second?.let {
            transformer.apply(numbers.second!!)
        }
        return combiner.apply(firstDigit, secondDigit)
    }

    //I felt  smart using regex but "oneight" obviously makes this strategy invalid
    fun part2(input: List<String>): Int {
        val pattern = Pattern.compile("(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)|\\d")
        val transformer = { captureGroup: String ->
            when (captureGroup) {
                "one" -> 1
                "two" -> 2
                "three" -> 3
                "four" -> 4
                "five" -> 5
                "six" -> 6
                "seven" -> 7
                "eight" -> 8
                "nine" -> 9
                else -> captureGroup.toInt()
            }
        }
        val preprocessor: (String) -> String = { line ->
            line.replace("one", "o1e")
                .replace("two", "t2o")
                .replace("three", "t3e")
                .replace("four", "f4r")
                .replace("five", "f5e")
                .replace("six", "s6x")
                .replace("seven", "s7n")
                .replace("eight", "e8t")
                .replace("nine", "n9e")
        }

        return input.foldRight(0) { line, previousValue ->
            val preprocessedLine = preprocessor(line)
            if (preprocessedLine != line) {
                logger.debug { "Line $line was preprocessed to $preprocessedLine" }
            }
            fold(preprocessedLine, previousValue, pattern, transformer, combiner)
        }

    }
}


