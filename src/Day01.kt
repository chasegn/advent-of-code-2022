import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_Test")
    val testResult = part1(testInput)
//    println(testResult)
    check(testResult == 24000)

    val input = readInput("Day01")
    println(part1(input)) // 69501
    println(part2(input)) // 202346
}
