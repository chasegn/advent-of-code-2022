import java.time.Duration
import kotlin.system.measureNanoTime

class Harness {
    private val day = Day10() // change this to swap out the day

    fun start() {
        day.tests()

        val input = readInput(day.inputFileName)

        val part1Time = Duration.ofNanos(measureNanoTime { println("part 1 result: " + day.part1(input)) }).toMillis()
        println("part 1 time: $part1Time ms \n")

        val part2Time = Duration.ofNanos(measureNanoTime { println("part 2 result: " + day.part2(input)) }).toMillis()
        println("part 2 time: $part2Time ms \n")
    }
}

fun main() {
    val harness = Harness()

    val overallTime = Duration.ofNanos(measureNanoTime { harness.start() }).toMillis()
    println("overall execution time: $overallTime ms")
}