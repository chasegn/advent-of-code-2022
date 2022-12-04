fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        for (contents in input) {
            val pockets = contents.splitAtIndex(contents.length / 2)
            run run@ {
                pockets.first.toCharArray().forEach { c ->
                    if (pockets.second.toCharArray().contains(c)) {
                        sum += convertCharToPriority(c)
                        return@run
                } }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.windowed(3, 3) {
            val one = it[0]
            val two = it[1]
            val three = it[2]

            run run@ {
                one.toCharArray().forEach { c ->
                    if (two.indexOf(c) != -1 && three.indexOf(c) != -1) {
                        sum += convertCharToPriority(c)
                        return@run
                    }
                }
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_Test")
    val testResult = part1(testInput)
    check(testResult == 157)
    val testResult2 = part2(testInput)
    check(testResult2 == 70)

    val input = readInput("Day03")
    println(part1(input)) // 7811
    println(part2(input)) // 2639
}

fun String.splitAtIndex(index: Int) = take(index) to substring(index)

// a: 97 -> 1
// A: 65 -> 27
fun convertCharToPriority(input: Char): Int {
    return when (input.isLowerCase()) {
        true -> input.code - 96
        false -> input.code - 38
    }
}