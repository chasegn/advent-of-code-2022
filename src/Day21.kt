import java.lang.IllegalArgumentException

/**
 * Day 21 for Advent of Code 2022
 * https://adventofcode.com/2022/day/21
 */
class Day21 : Day {
    override val inputFileName: String = "Day21"
    override val test1Expected: Long = 152
    override val test2Expected: Long = 301

    /**
     * Accepted solution: 110181395003396
     * 135 ms
     */
    override fun part1(input: List<String>): Long {
        val monkeys = buildMonkeys(input)

        while (!isNumeric(monkeys["root"]!!)) {
            for (monkey in monkeys) {
                if (!isNumeric(monkey.value)) {
                    val parts = monkey.value.split("[+-/*]".toRegex())
                    val monkeyA = monkeys[parts[0].trim()]
                    val monkeyB = monkeys[parts[1].trim()]
                    val op = "[+-/*]".toRegex().find(monkey.value)!!.value

                    if (isNumeric(monkeyA!!) && isNumeric(monkeyB!!)) {
                        val monkeyAVal = monkeyA.toLong()
                        val monkeyBVal = monkeyB.toLong()

                        monkeys[monkey.key] = when(op) {
                            "+" -> (monkeyAVal + monkeyBVal).toString()
                            "-" -> (monkeyAVal - monkeyBVal).toString()
                            "*" -> (monkeyAVal * monkeyBVal).toString()
                            "/" -> (monkeyAVal / monkeyBVal).toString()
                            else -> throw IllegalArgumentException("Did not recognize symbol $op")
                        }
                        println("remapped monkey ${monkey.key} to ${monkeys[monkey.key]}")
                    }
                }
            }
        }

        return monkeys["root"]!!.toLong()
    }

    /**
     * Accepted solution:
     */
    override fun part2(input: List<String>): Int {
        return input.size
    }

    private fun buildMonkeys(input: List<String>, isPart2: Boolean = false): MutableMap<String, String> {
        val monkeys = HashMap<String, String>()

        input.forEach {
            val tokens = it.split(':')

            if (!monkeys.containsKey(tokens[0])) {
                monkeys[tokens[0].trim()] = tokens[1].trim()
            }
        }

        if (isPart2) {
            monkeys["humn"] = "what"
            monkeys["root"] = "[+-/*]".toRegex().replace(monkeys["root"]!!, "==")
        }

        return monkeys
    }

    private fun isNumeric(toCheck: String): Boolean {
        return toCheck.all { c -> c.isDigit() }
    }
}