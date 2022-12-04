import kotlin.system.measureTimeMillis

fun main() {
    println("overall execution time: " + measureTimeMillis { start() })
}

fun start() {

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_Test")
    val testResult = part1(testInput)
    println("part 1 test result: $testResult")
    check(testResult == 2)
    val testResult2 = part2(testInput)
    println("part 2 test result: $testResult2 \n")
    check(testResult2 == 4)

    val input = readInput("Day04")
    println("part 1 time: " + measureTimeMillis { println("part 1 result: " + part1(input)) } + "\n") // 644
    println("part 2 time: " + measureTimeMillis { println("part 2 result: " + part2(input)) } + "\n") // 926
}

fun part1(input: List<String>): Int {
    var count = 0;

    for (assignment in input) {
        val ranges = makeRanges(assignment)

        if ((ranges.first.contains(ranges.second.first) && ranges.first.contains(ranges.second.last)) ||
            (ranges.second.contains(ranges.first.first) && ranges.second.contains(ranges.first.last))) {
            count++
        }
    }

    return count
}

fun part2(input: List<String>): Int {
    var count = 0;

    for (assignment in input) {
        val ranges = makeRanges(assignment)

        if ((ranges.first.contains(ranges.second.first) || ranges.first.contains(ranges.second.last)) ||
            (ranges.second.contains(ranges.first.first) || ranges.second.contains(ranges.first.last))) {
            count++
        }
    }

    return count
}

fun makeRanges(input: String): Pair<IntRange, IntRange> {
    val pair = input.split(',')
    val splitPair = Pair(pair[0].split('-'), pair[1].split('-'))

    val left = splitPair.first[0].toInt()..splitPair.first[1].toInt()
    val right = splitPair.second[0].toInt()..splitPair.second[1].toInt()

    return Pair(left, right)
}