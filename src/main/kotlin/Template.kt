import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class Template {


    fun part1(input: List<String>): Long {
        return TemplatePart1().part1(input)
    }

    fun part2(input: List<String>): Long {
        return TemplatePart2().part2(input)
    }
}

private class TemplateCommon {

}

private class TemplatePart1 {
    fun part1(input: List<String>): Long {
        return 0
    }
}

private class TemplatePart2 {
    fun part2(input: List<String>): Long {
        return 0
    }
}
