
/**
 * Day 04 for Advent of Code 2022
 * https://adventofcode.com/2022/day/4
 */
class Day04 : Day {
    override val inputFileName: String = "Day04"
    override val test1Expected: Int = 2
    override val test2Expected: Int = 4

    /**
     * Accepted solution: 644
     */
    override fun part1(input: List<String>): Int {
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

    /**
     * Accepted solution: 926
     */
    override fun part2(input: List<String>): Int {
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

    private fun makeRanges(input: String): Pair<IntRange, IntRange> {
        val pair = input.split(',')
        val splitPair = Pair(pair[0].split('-'), pair[1].split('-'))

        val left = splitPair.first[0].toInt()..splitPair.first[1].toInt()
        val right = splitPair.second[0].toInt()..splitPair.second[1].toInt()

        return Pair(left, right)
    }
}