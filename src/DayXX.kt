fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01")
    val testResult = part1(testInput)
//    println(testResult)
    check(testResult == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
