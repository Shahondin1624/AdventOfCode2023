import mu.KotlinLogging
import java.util.concurrent.atomic.AtomicLong

class Day07 {
    fun part1(input: List<String>): Long {
        return Part1().part1(input)
    }

    fun part2(input: List<String>): Long {
        return Part2().part2(input)
    }

}

private val logger = KotlinLogging.logger {}

private fun rate(hands: List<Hand>, cardStrength: Map<Char, Int>): Long {
    val counter = AtomicLong(1L)

    return hands.sortedWith(comparator(cardStrength))
        .fold(0L) { previous, hand ->
            val rank = counter.getAndIncrement()
            val score = hand.bid * rank

            // Log each hand details
            logger.debug { "Hand Cards: ${hand.cards}, Bid: ${hand.bid}, Rank: $rank, Current Score (Bid * Rank): $score" }

            previous + score
        }
}

data class Hand(val cards: String, val bid: Long, val occurrences: Map<Char, Long>) {
    companion object Parser {
        fun parse(input: List<String>): List<Hand> {
            return input.map { line ->
                val split = line.split("\\s+".toRegex())
                Hand(split[0], split[1].toLong(), mapChars(split[0]))
            }.toList()
        }
    }
}

fun mapChars(card: String): Map<Char, Long> {
    val charOccurrences = mutableMapOf<Char, Long>()
    for (char in card) {
        charOccurrences[char] = (charOccurrences[char] ?: 0) + 1
    }
    return charOccurrences
}

data class Score(val hand: Hand, val type: Int) {
    companion object Scorer {
        fun score(hand: Hand): Score {
            val badName: Int = if (fiveOfAKind(hand)) {
                20
            } else if (fourOfAKind(hand)) {
                16
            } else if (fullHouse(hand)) {
                10
            } else if (threeOfAKind(hand)) {
                6
            } else if (twoPairs(hand)) {
                4
            } else if (onePair(hand)) {
                2
            } else {
                1
            }
            return Score(hand, badName)
        }


        private fun fiveOfAKind(hand: Hand): Boolean {
            return hand.occurrences.values.max() == 5L
        }

        private fun fourOfAKind(hand: Hand): Boolean {
            return hand.occurrences.values.max() == 4L
        }

        private fun fullHouse(hand: Hand): Boolean {
            return hand.occurrences.values.contains(3L) && hand.occurrences.values.contains(2L)
        }

        private fun threeOfAKind(hand: Hand): Boolean {
            return hand.occurrences.values.max() == 3L
        }

        private fun twoPairs(hand: Hand): Boolean {
            return fullHouse(hand).not() && hand.occurrences.values.count { it == 2L } == 2
        }

        private fun onePair(hand: Hand): Boolean {
            return fullHouse(hand).not() && twoPairs(hand).not() && hand.occurrences.values.contains(2L)
        }
    }
}

private fun comparator(cardStrength: Map<Char, Int>) = Comparator.comparingInt<Hand> { Score.score(it).type }
    .then(positionComparator(0, cardStrength))
    .then(positionComparator(1, cardStrength))
    .then(positionComparator(2, cardStrength))
    .then(positionComparator(3, cardStrength))
    .then(positionComparator(4, cardStrength))

private fun positionComparator(index: Int, cardStrength: Map<Char, Int>) = Comparator<Hand> { hand1, hand2 ->
    cardStrength.getValue(hand1.cards[index]).compareTo(cardStrength.getValue(hand2.cards[index]))
}

private class Part1 {
    fun part1(input: List<String>): Long {
        val hands = Hand.parse(input)
        return rate(hands, cardStrength)
    }


    val cardStrength: Map<Char, Int> = mapOf(
        'A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10,
        '9' to 9, '8' to 8, '7' to 7, '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2
    )


}

private class Part2 {
    fun part2(input: List<String>): Long {
        val hands = Hand.parse(input)
            .map { preprocessHand(it) }
        return rate(hands, cardStrength)
    }

    val cardStrength: Map<Char, Int> = mapOf(
        'J' to 1,
        '2' to 2,
        '3' to 3,
        '4' to 4,
        '5' to 5,
        '6' to 6,
        '7' to 7,
        '8' to 8,
        '9' to 9,
        'T' to 10,
        'Q' to 11,
        'K' to 12,
        'A' to 13
    )

    private fun preprocessHand(hand: Hand): Hand {
        val js = hand.occurrences['J'] ?: 0L
        val (char, amount) = hand.occurrences.filterNot { it.key == 'J' }.maxByOrNull { it.value } ?: return hand // filter out 'J'
        val newOccurrences = hand.occurrences.toMutableMap()
        newOccurrences[char] = amount + js
        // If there are 'J's, set the count to 0 after adding to another card.
        if (js > 0) {
            newOccurrences['J'] = 0
        }
        return Hand(hand.cards, hand.bid, newOccurrences)
    }
}