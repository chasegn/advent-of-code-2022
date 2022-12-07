import java.util.*

/**
 * Day 05 for Advent of Code 2022
 * https://adventofcode.com/2022/day/5
 */
class Day05 : Day {
    override val inputFileName: String = "Day05"
    override val test1Expected: String = "CMZ"
    override val test2Expected: String = "MCD"

    /**
     * Accepted solution: "CNSZFDVLJ"
     */
    override fun part1(input: List<String>): String {
        val iter = input.listIterator()
        val stacks = prepareCrates(iter)

        iter.next()

        do {
            moveCrates(stacks, iter.next())
        } while(iter.hasNext())

        return getTopsOfStacksAsString(stacks)
    }

    /**
     * Accepted solution: "QNDWLMGNS"
     */
    override fun part2(input: List<String>): String {
        val iter = input.listIterator()
        val stacks = prepareCrates(iter)

        iter.next()

        do {
            moveCrates(stacks, iter.next(), true)
        } while (iter.hasNext())

        return getTopsOfStacksAsString(stacks)
    }

    private fun prepareCrates(iter: ListIterator<String>): List<Stack<Char>> {
        val stacks = mutableListOf<Stack<Char>>()
        val prep = LinkedList<String>()
        var line = iter.next()

        // traverse the initial test data to get to the stack numbers, holding onto the crates lines for later
        do {
            prep.add(line)
            line = iter.next()
        } while (line.contains('['))

        // line now has the line with the stack numbers, grab the last one and create that many stacks
        repeat(line.split(' ').last().toInt()) {
            stacks.add(Stack<Char>())
        }

        // flip the list of saved lines of crates and iteratively build the stacks
        prep.reversed().forEach {
            buildStacks(stacks, it)
        }

        return stacks
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

            // past the first index, every fourth index is the extra space between stack numbers
            if (charPos > 4 && charPos % 4 == 1) {
                stackPos++
            }
        }
    }

    private fun moveCrates(stacks: List<Stack<Char>>, instruction: String, moveMany: Boolean = false) {
        val instructionParts = instruction.split(' ')
        val qty = instructionParts[1].toInt()
        val src = stacks[instructionParts[3].toInt() - 1]
        val dest = stacks[instructionParts[5].toInt() - 1]

        if (moveMany) {
            // move many crates at once, keeping their order
            val moveds = mutableListOf<Char>()
            repeat(qty) { moveds.add(src.pop()) }
            dest.addAll(moveds.reversed())
        } else {
            // typical tower of hanoi functionality
            repeat(qty) { dest.push(src.pop()) }
        }
    }

    private fun getTopsOfStacksAsString(stacks: List<Stack<Char>>): String {
        var result = ""

        stacks.forEach {
            result += when (it.size) {
                0 -> ' '
                else -> it.peek()
            }
        }

        return result
    }
}
