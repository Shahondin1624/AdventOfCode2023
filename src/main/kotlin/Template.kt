import mu.KotlinLogging

class Template {
    private val logger = KotlinLogging.logger {}

    fun part1(input: List<String>): Long {
        return TemplatePart1().part1(input)
    }

    fun part2(input: List<String>): Long {
        return TemplatePart2().part2(input)
    }

    private class TemplateCommon {

    }

    private class TemplatePart1 {
        fun part1(input: List<String>): Long {
            TODO()
        }
    }

    private class TemplatePart2 {
        fun part2(input: List<String>): Long {
            TODO()
        }
    }
}


