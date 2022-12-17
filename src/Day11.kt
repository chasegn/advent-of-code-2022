import java.lang.IllegalArgumentException

/**
 * Day 11 for Advent of Code 2022
 * https://adventofcode.com/2022/day/11
 */
class Day11 : Day {
    override val inputFileName: String = "Day11"
    override val test1Expected: Long? = null
//    override val test1Expected: Long = 10605
    override val test2Expected: Long = 2713310158

    /**
     * Accepted solution: 95472
     */
    override fun part1(input: List<String>): Long {
        val monkeys = buildMonkeys(input)
        return engageMonkeyBusiness(monkeys, 20, true, 3)
    }

    /**
     * Accepted solution: 17926061332
     */
    override fun part2(input: List<String>): Long {
        val monkeys = buildMonkeys(input)
        val lcm = monkeys
            .map { it.testDivisor }
            .reduce { acc, l -> acc * l }
        return engageMonkeyBusiness(monkeys, 10000, false, lcm)
    }

    private fun engageMonkeyBusiness(monkeys: List<Monkey>, rounds: Int, canRelax: Boolean, factor: Long): Long {
        for (round in 1..rounds) {
            for (monkey in monkeys) {
                monkey.items.forEach { item ->
                    val worry = when (canRelax) {
                        true -> monkey.inspectAndRelax(item, factor)
                        false -> monkey.inspectNoRelax(item, factor)
                    }

                    if (monkey.testWorry(worry)) {
                        monkeys[monkey.targets.first].items.add(worry)
                    } else {
                        monkeys[monkey.targets.second].items.add(worry)
                    }
                }

                monkey.items.removeAll { true }
            }

            // for printing output
            when (canRelax) {
                true -> { printMonkeyInventory(round, monkeys) }
                false -> {
                    val interestingRounds = intArrayOf(1, 20, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000)
                    if (interestingRounds.contains(round)) {
                        printMonkeyInspections(round, monkeys)
                    }
                }
            }
        }

        val topTwo = monkeys.map { it.inspections }.sortedDescending().take(2)

        println(topTwo[0] * topTwo[1])

        return topTwo[0] * topTwo[1]
    }

    private fun printMonkeyInventory(round: Int, monkeys: List<Monkey>) {
        println("After round $round, the monkeys are holding items with these worry levels:")
        for (monkey in monkeys) {
            println("Monkey ${monkey.id}: ${monkey.items}")
        }
        println()
    }

    private fun printMonkeyInspections(round: Int, monkeys: List<Monkey>) {
        println("=== After round $round ===")
        monkeys.forEach{ println("Monkey ${it.id} inspected ${it.inspections} times") }
        println()
    }

    private fun buildMonkeys(input: List<String>): MutableList<Monkey> {
        val monkeys = mutableListOf<Monkey>()

        input.chunked(7).forEach { inputChunk ->
            val monkeyId = inputChunk[0].split(' ')[1].split(':')[0].toInt()
            val startingItems = inputChunk[1].split(':')[1].trim().split(", ").map { it.toLong() }.toMutableList()
            val operation = inputChunk[2].trim()
            val test = inputChunk[3].trim().split(' ')[3].toLong()
            val targetA = inputChunk[4].trim().split(' ')[5].toInt()
            val targetB = inputChunk[5].trim().split(' ')[5].toInt()

            monkeys.add(Monkey(monkeyId, startingItems, operation, test, Pair(targetA, targetB)))
        }

        return monkeys
    }

    class Monkey(val id: Int, val items: MutableList<Long>, operation: String, val testDivisor: Long, val targets: Pair<Int, Int>) {
        val operation: ItemInspection
        var inspections = 0L

        init {
            this.operation = buildInspection(operation)
        }

        private fun buildInspection(input: String): ItemInspection {
            val tokens = input.removePrefix("Operation: new = old ").split(" ")

            val operator: (ItemWorryLevel, ItemWorryLevel) -> ItemWorryLevel =
                when (tokens[0]) {
                    "*" -> Long::times
                    "+" -> Long::plus
                    else -> throw IllegalArgumentException("unknown operation: ${tokens[0]}")
            }

            val arg = tokens[1].toLongOrNull()

            return { old ->
                if (arg == null) operator(old, old)
                else operator(old, arg)
            }
        }

        fun inspectAndRelax(input: Long, factor: Long): Long {
            inspections++
            return operation(input) / factor
        }

        fun inspectNoRelax(input: Long, factor: Long): Long {
            inspections++
            return operation(input) % factor
        }

        fun testWorry(input: Long): Boolean {
            return input.mod(testDivisor) == 0L
        }
    }
}

private typealias ItemWorryLevel = Long
private typealias ItemInspection = (ItemWorryLevel) -> ItemWorryLevel
