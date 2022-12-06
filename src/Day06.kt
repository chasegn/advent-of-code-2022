
/**
 * Day 06 for Advent of Code 2022
 * https://adventofcode.com/2022/day/6
 */
class Day06 : Day {
    override val inputFileName: String = "Day06"
    /**
     * Test data for this one was kind of weird. Each line from the test file has its own value.
     * The test for the second part doubles the number of answers.
     * Thus, if you look at the test file for this day, I append the part 1 and part 2 answers to the end of the test line.
     * e.g. mjqjpqmgbljsphdztnvjfqwrcgsmlb-7-19
     * With "4 unique per 4" (part 1), the answer is 7
     * With "14 unique per 14" (part 2), the answer is 19
     */
    override val test1Expected: Int? = null
    override val test2Expected: Int? = null

    /**
     * Accepted solution: 1142
     */
    override fun part1(input: List<String>): Int {
//        val marker = input[0].windowed(4, 1).first {
//            it.allUnique()
//        }
//
//        return input[0].indexOf(marker) + 4
        return findFirstUniqueSequence(input[0], 4)
    }

    /**
     * Accepted solution: 2803
     */
    override fun part2(input: List<String>): Int {
//        val marker = input[0].windowed(14, 1).first {
//            it.allUnique()
//        }
//
//        return input[0].indexOf(marker) + 14

        return findFirstUniqueSequence(input[0], 14)
    }

    private fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)

    private fun findFirstUniqueSequence(input: String, length: Int): Int {
        return input.indexOf(input.windowed(length).first{it.all(hashSetOf<Char>()::add)}) + length
    }
}