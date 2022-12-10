
/**
 * Day 10 for Advent of Code 2022
 * https://adventofcode.com/2022/day/10
 */
class Day10 : Day {
    override val inputFileName: String = "Day10"
    override val test1Expected: Int = 13140
    override val test2Expected: Int? = null

    /**
     * Accepted solution:
     */
    override fun part1(input: List<String>): Int {
        var register = 1
        var cycleCount = 0
        var total = 0

        fun tick() {
            cycleCount++

            if (cycleCount == 20 || (cycleCount + 20) % 40 == 0) {
                total += (register * cycleCount)
            }
        }

        input.forEach {
            tick()

            if (it[0] == 'a') {
                tick()
                register += it.split(' ')[1].toInt()
            }
        }

        return total
    }

    /**
     * Accepted solution:
     */
    override fun part2(input: List<String>): Int {
        return input.size
    }


}