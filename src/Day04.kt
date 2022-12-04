import kotlin.ranges.rangeTo
fun main() {
    fun part1(input: List<String>): Int {
        var count = 0;

        for (assignment in input) {
            val pair = assignment.split(',')
            val first = pair[0].split('-')
            val second = pair[1].split('-')
            val left = first[0].toInt()..first[1].toInt()
            val right = second[0].toInt()..second[1].toInt()
            if ((left.contains(right.first) && left.contains(right.last)) ||
                    (right.contains(left.first) && right.contains(left.last)))
                count++
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0;

        for (assignment in input) {
            val pair = assignment.split(',')
            val first = pair[0].split('-')
            val second = pair[1].split('-')
            val left = first[0].toInt()..first[1].toInt()
            val right = second[0].toInt()..second[1].toInt()
            if ((left.contains(right.first) || left.contains(right.last)) ||
                (right.contains(left.first) || right.contains(left.last)))
                count++
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_Test")
    val testResult = part1(testInput)
    println(testResult)
    check(testResult == 2)
    val testResult2 = part2(testInput)
    println(testResult2)
    check(testResult2 == 4)

    val input = readInput("Day04")
    println(part1(input)) // 644
    println(part2(input)) // 926
}
