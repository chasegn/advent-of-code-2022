fun main() {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_Test")
    val testResult = part1(testInput)
    println(testResult)
    check(testResult == 2)
    val testResult2 = part2(testInput)
    println(testResult2)
    check(testResult2 == 4)

    val input = readInput("Day04")
    println(part1(input)) // 644
    println(part2(input)) // 926
}

fun makeRanges(input: String): Pair<IntRange, IntRange> {
    val pair = input.split(',')
    val splitPair = Pair(pair[0].split('-'), pair[1].split('-'))

    val left = splitPair.first[0].toInt()..splitPair.first[1].toInt()
    val right = splitPair.second[0].toInt()..splitPair.second[1].toInt()

    return Pair(left, right)
}
