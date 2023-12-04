import mu.KotlinLogging
import kotlin.math.pow

class Day04 {
    private val logger = KotlinLogging.logger {}

    fun part1(input: List<String>): Int {
        return input.map { parseLine(it) }
            .map { card -> getPoints(card) }
            .fold(0) { previous, now -> previous + now }
    }


    fun part2(input: List<String>): Int {
        val cards = input.map { parseLine(it) }.toList()
        val cardAmountMap = initializeCardMap(cards)
        firstIteration(cards, cardAmountMap)
        for ((index, amount) in cardAmountMap.values.withIndex()) {
            val cardNumber = index + 1
            val cardsWon = getAmountOfWinningNumbers(cards.getCardByNumber(cardNumber))
            val lower = cardNumber.inc()
            val range = lower..<lower + cardsWon
            for (i in range) {
                cardAmountMap[i] = cardAmountMap[i]!! + amount
            }
        }
        return cardAmountMap.values.sum() + cards.size
    }

    private fun firstIteration(
        cards: List<Card>,
        cardAmountMap: MutableMap<Int, Int>
    ) {
        for ((index, card) in cards.withIndex()) {
            val cardIndex = index + 1
            val cardsWon = cardIndex + getAmountOfWinningNumbers(card)
            val range = cardIndex + 1..cardsWon
            for (i in range) {
                cardAmountMap[i] = cardAmountMap[i]!!.inc()
            }
        }
    }

    private fun initializeCardMap(cards: List<Card>): MutableMap<Int, Int> =
        cards.associate { it.number to 0 }.toMutableMap()
}

data class Card(val number: Int, val winningNumbers: List<Int>, val numbers: List<Int>)

//Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
fun parseLine(line: String): Card {
    val outer = line.split(":".toRegex())
    val cardNumber = outer[0].split("\\s+".toRegex())[1].toInt()
    val numbers = outer[1].split("\\|".toRegex())
    val winningNumbers = numbers[0].split("\\s+".toRegex()).parseToInts()
    val myNumbers = numbers[1].split("\\s+".toRegex()).parseToInts()
    return Card(cardNumber, winningNumbers, myNumbers)
}

fun getPoints(card: Card): Int {
    val winningNumbers = getAmountOfWinningNumbers(card)
    return if (winningNumbers == 0) 0 else power2(winningNumbers - 1)
}

fun getAmountOfWinningNumbers(card: Card) = card.numbers.count { card.winningNumbers.contains(it) }


private fun List<String>.parseToInts(): List<Int> {
    return this.filter { it.isNotEmpty() }
        .map { it.toInt() }
}

private fun List<Card>.getCardByNumber(number: Int): Card {
    return this[number - 1] //Possible out IndexOutOfBoundsException is intentional here
}

private fun power2(exponent: Int): Int {
    val base = 2.0
    val exp = exponent.toDouble()
    return base.pow(exp).toInt()
}