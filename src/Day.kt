interface Day {
    val inputFileName: String
    val testFileName: String
        get() = inputFileName + "_Test"

    val test1Expected: Int
    val test2Expected: Int

    fun part1(input: List<String>): Int
    fun part2(input: List<String>): Int

    fun tests() {
        val testInput = readInput(testFileName)

        if (test1Expected != -1) {
            val test1Result = part1(testInput)
            check(test1Result == test1Expected) {
                """
                    part 1 test failed
                    Expected:  $test1Expected
                    Actual:    $test1Result
                """.trimIndent()
            }
        }

        if (test2Expected != -1) {
            val test2Result = part2(testInput)
            check(test2Result == test2Expected) {
                """
                    part 2 test failed
                    Expected: $test2Expected
                    Actual:   $test2Result
                """.trimIndent()
            }
        }
    }
}