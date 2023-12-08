import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class Day08 {

    fun part1(input: List<String>): Long {
        return Day08Part1().part1(input)
    }

    fun part2(input: List<String>): Long {
        return Day08Part2().part2(input)
    }

    private class Day08Common {
        companion object Parser {
            fun parse(input: List<String>): Pair<MovementDescription, Map<String, NodeDescription>> {
                val movementDescription = MovementDescription(input[0])
                //at index 2 because the first line is already parsed,
                // second line is empty and third is the first relevant one
                val map = input.subList(2, input.size).map { NodeDescription.parseLine(it) }.associateBy { it.name }
                return Pair(movementDescription, map)
            }
        }

        data class NodeDescription(val name: String, val left: String, val right: String) {
            companion object Parser {
                fun parseLine(line: String): NodeDescription {
                    val pattern = "\\w{3}".toRegex().toPattern()
                    val matcher = pattern.matcher(line)
                    val elements = matcher.collectMatchingGroups()
                    return NodeDescription(elements[0], elements[1], elements[2])
                }
            }

            override fun toString(): String {
                return "$name->(left:$left)(right:$right)"
            }
        }

        class MovementDescription(line: String) {
            private val directions: List<String> = line.split("").filter { it.isEmpty().not() }.toList()
            private var pointer = 0

            fun nextDirection(): String {
                if (pointer >= directions.size) {
                    pointer = 0
                }
                return directions[pointer++]
            }
        }
    }

    private class Day08Part1 {
        fun part1(input: List<String>): Long {
            val (movementDescription, map) = Day08Common.parse(input)
            var nodeDescription = map["AAA"]!!
            var iterations = 0L
            while (nodeDescription.name != "ZZZ") {
                val direction = movementDescription.nextDirection()
                nodeDescription = map[if (direction == "R") nodeDescription.right else nodeDescription.left]!!
                iterations++
            }
            return iterations
        }
    }

    private class Day08Part2 {
        fun part2(input: List<String>): Long {
            val (movementDescription, map) = Day08Common.parse(input)
            val nodes = map.filter { it.key.endsWith("A") }.map { it.value }
            val distances = nodes.map { getWalkToZIterations(it, movementDescription, map) }
            return lcm(distances)
        }

        private fun getWalkToZIterations(
            node: Day08Common.NodeDescription,
            movementDescription: Day08Common.MovementDescription,
            map: Map<String, Day08Common.NodeDescription>
        ): Long {
            var nodeDescription = node
            var iterations = 0L
            while (!nodeDescription.name.endsWith("Z")) {
                val direction = movementDescription.nextDirection()
                nodeDescription = map[if (direction == "R") nodeDescription.right else nodeDescription.left]!!
                iterations++
            }
            return iterations
        }

        //The Obvious first move is brute forcing a solution.
        // r\AdventOfCode, however, spoiled that it would take about 14 trillion iterations, which is a very long time
        private fun bruteForceMadness(
            nodes: List<Day08Common.NodeDescription>,
            movementDescription: Day08Common.MovementDescription,
            map: Map<String, Day08Common.NodeDescription>
        ): Long {
            var copy = nodes
            var iterations = 0L
            while (!copy.all { it.name.endsWith("Z") }) {
                if (iterations % 1_000_000_000L == 0L) {
                    logger.debug { "Iteration ${String.format("%.3e", iterations.toDouble())}" }
                }
                val direction = movementDescription.nextDirection()
                val nextNodes = copy
                    .map { if (direction == "R") map[it.right]!! else map[it.left]!! }
                copy = nextNodes
                iterations++
            }
            return iterations
        }
    }
}


