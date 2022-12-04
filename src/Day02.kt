
fun main() {
    fun part1(input: List<String>): Int {
        var score = 0

        /*
        A: 65  1  X: 88 (-23 = 65)
        B: 66  2  Y: 89
        C: 67  3  Z: 90
         */
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

    /*
    A: 65   X: lose
    B: 66   Y: draw
    C: 67   Z: win
     */
    fun part2(input: List<String>): Int {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_Test")
    val testResult = part1(testInput)
    check(testResult == 15)
    val testResult2 = part2(testInput)
    check(testResult2 == 12)

    val input = readInput("Day02")
    println(part1(input)) // 13924
    println(part2(input)) // 13448
}

fun beatElf(input: Char): Char {
    return when (input) {
        'A' -> 'B'
        'B' -> 'C'
        'C' -> 'A'
        else -> 'D'
    }
}

fun loseElf(input: Char): Char {
    return when (input) {
        'A' -> 'C'
        'B' -> 'A'
        'C' -> 'B'
        else -> 'D'
    }
}