
/**
 * Day 03 for Advent of Code 2022
 * https://adventofcode.com/2022/day/3
 */
class Day03 : Day {
    override val inputFileName: String = "Day03"
    override val test1Expected: Int = 157
    override val test2Expected: Int = 70

    /**
     * Accepted solution: 7811
     */
    override fun part1(input: List<String>): Int {
        var sum = 0

        for (contents in input) {
            val pockets = contents.splitAtIndex(contents.length / 2)
            run run@{
                pockets.first.toCharArray().forEach { c ->
                    if (pockets.second.toCharArray().contains(c)) {
                        sum += convertCharToPriority(c)
                        return@run
                    }
                }
            }
        }

        return sum
    }

    /**
     * Accepted solution: 2639
     */
    override fun part2(input: List<String>): Int {
        var sum = 0

        input.windowed(3, 3) {
            run run@{
                it[0].toCharArray().forEach { c ->
                    if (it[1].indexOf(c) != -1 && it[2].indexOf(c) != -1) {
                        sum += convertCharToPriority(c)
                        return@run
                    }
                }
            }
        }

        return sum
    }

    /**
     * a: 97 -> 1
     * A: 65 -> 27
     */
    private fun convertCharToPriority(input: Char): Int {
        return when (input.isLowerCase()) {
            true -> input.code - 96
            false -> input.code - 38
        }
    }
}