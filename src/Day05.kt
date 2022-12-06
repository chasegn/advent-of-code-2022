import java.util.*

/**
 * Day 05 for Advent of Code 2022
 * https://adventofcode.com/2022/day/5
 */
class Day05 : Day {
    override val inputFileName: String = "Day05"
    override val test1Expected: String = "CMZ"
    override val test2Expected: String? = null

    /**
     * Accepted solution: "CNSZFDVLJ"
     */
    override fun part1(input: List<String>): String {
        val prep = LinkedList<String>()
        val iter = input.listIterator()
        var line = iter.next()

        do {
            prep.add(line)
            line = iter.next()
        } while (line.contains('['))
        // after loop, line should be line with numbers

        val stacks = mutableListOf<Stack<Char>>()
        val stackCount = line[line.lastIndex].digitToInt()
        for (i in 1..stackCount) {
            stacks.add(Stack<Char>())
        }

        prep.reversed().forEach {
            buildStacks(stacks, it)
        }

        iter.next()

        do {
            moveCrates(stacks, iter.next())
        } while(iter.hasNext())

        var result = ""
        stacks.forEach{
            result += when (it.size) {
                0 -> ' '
                else -> it.peek()
            }
        }

        return result
    }

    /**
     * Accepted solution:
     */
    override fun part2(input: List<String>): Int {
        return input.size
    }

    private fun moveCrates(stacks: List<Stack<Char>>, instruction: String) {
        val instructionParts = instruction.split(' ')
        val qty = instructionParts[1].toInt()
        val src = stacks[instructionParts[3].toInt() - 1]
        val dest = stacks[instructionParts[5].toInt() - 1]

        for (i in 1..qty) {
            dest.push(src.pop())
        }
    }

    private fun buildStacks(stacks: List<Stack<Char>>, line: String) {
        var stackPos = 0 // counts stack index
        var charPos = 0  // counts between crate character locations
        val iter = line.iterator()

        while (iter.hasNext()) {
            val c = iter.next()

            if (c != '[' && c != ']' && c != ' ') {
                if (c.isUpperCase() || c.isLowerCase()) {
                    stacks[stackPos].push(c)
                }
            }

            charPos++
            if (charPos > 4 && charPos % 4 == 1) {
                stackPos++
            }
        }
    }
}
