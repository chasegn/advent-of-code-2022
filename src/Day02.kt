
/**
 * Day 02 for Advent of Code 2022
 * https://adventofcode.com/2022/day/2
 */
class Day02 : Day {
    override val inputFileName: String = "Day02"
    override val test1Expected: Int = 15
    override val test2Expected: Int = 12

    /**
     * A: 65  1  X: 88 (-23 = 65)
     * B: 66  2  Y: 89
     * C: 67  3  Z: 90
     *
     * Accepted solution: 13924
     */
    override fun part1(input: List<String>): Int {
        var score = 0

        for (attempt in input) {
            val elf = attempt[0].code
            val you = attempt[2].code - 20

            score +=
                (if (you - elf == 1 || you - elf == 4) {
                    // win
                    (6 + (you - 67))
                } else if (you - elf == 3) {
                    // draw
                    (3 + (you - 67))
                } else {
                    // lose
                    (you - 67)
                })
        }

        return score
    }

    /**
     * A: 65   X: lose
     * B: 66   Y: draw
     * C: 67   Z: win
     *
     * Accepted solution: 13448
     */
    override fun part2(input: List<String>): Int {
        var score = 0

        for (attempt in input) {
            val elf = attempt[0]
            val result = attempt[2]

            score += when (result) {
                'X' -> loseElf(elf).code - 64 // loss
                'Y' -> (3 + elf.code - 64) // draw
                else -> (6 + beatElf(elf).code - 64) // win
            }
        }

        return score
    }

    private fun beatElf(input: Char): Char {
        return when (input) {
            'A' -> 'B'
            'B' -> 'C'
            'C' -> 'A'
            else -> 'D'
        }
    }

    private fun loseElf(input: Char): Char {
        return when (input) {
            'A' -> 'C'
            'B' -> 'A'
            'C' -> 'B'
            else -> 'D'
        }
    }
}