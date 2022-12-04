import java.lang.Integer.max

/**
 * Day 01 for Advent of Code 2022
 * https://adventofcode.com/2022/day/1
 */
class Day01 : Day {
    override val inputFileName: String = "Day01"
    override val test1Expected: Int = 24000
    override val test2Expected: Int = 45000

    /**
     * Accepted solution: 69501
     */
    override fun part1(input: List<String>): Int {
        var curSum = 0
        var savedMax = 0

        for (weight in input) {
            if (weight.isNotEmpty()) {
                curSum += weight.toInt()
            } else {
                savedMax = max(savedMax, curSum)
                curSum = 0
            }
        }

        return savedMax
    }

    /**
     * Accepted solution: 202346
     */
    override fun part2(input: List<String>): Int {
        val list = mutableListOf<Int>()
        var curSum = 0

        for (weight in input) {
            if (weight.isNotEmpty()) {
                curSum += weight.toInt()
            } else {
                list.add(curSum)
                curSum = 0
            }
        }

        list.sortDescending()

        return list.slice(0..2).sumOf { it }
    }
}