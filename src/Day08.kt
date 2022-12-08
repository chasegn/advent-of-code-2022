
/**
 * Day 08 for Advent of Code 2022
 * https://adventofcode.com/2022/day/8
 */
class Day08 : Day {
    override val inputFileName: String = "Day08"
    override val test1Expected: Int? = null
//    override val test1Expected: Int = 21
    override val test2Expected: Int? = null

    /**
     * Accepted solution: 1711
     */
    override fun part1(input: List<String>): Int {
        val trees = buildTreeGrid(input)
        var result = 0

        for (i in 1 until trees.size-1) {
            for (j in 1 until trees.size-1) {
                print("$i,$j: ${trees[i]!![j]}")
                if (isVisible(trees, i, j)) {
                    result++
                    print(" vis")
                }
                print("\n")
            }
        }

        print("\n")
        return result + ((trees[0]!!.size - 1)*2 + (trees.size - 1)*2)
    }

    /**
     * Accepted solution:
     */
    override fun part2(input: List<String>): Int {
        return input.size
    }

    private fun buildTreeGrid(input: List<String>): Array<Array<Int>?> {
        val trees = arrayOfNulls<Array<Int>>(input.size)

        input.forEachIndexed { idx, line ->
            trees[idx] = line.toCharArray().map { it.digitToInt() }.toTypedArray()
        }

        return trees
    }

    private fun isVisible(trees: Array<Array<Int>?>, x: Int, y: Int): Boolean {
        val curHeight = (trees[x]?.get(y))!!

        // quick check if neighbors are all taller
        var blockedNorth = trees[x-1]!![y] >= curHeight
        var blockedWest = trees[x]!![y-1] >= curHeight
        var blockedEast = trees[x]!![y+1] >= curHeight
        var blockedSouth = trees[x+1]!![y] >= curHeight

        // check north (--x)
        if (!blockedNorth) {
            for (i in (x-1) downTo 0) {
                if (trees[i]!![y] >= curHeight) {
                    blockedNorth = true
                    break
                }
            }
        }

        // check west (--y)
        if (!blockedWest) {
            for (i in (y-1)downTo 0) {
                if (trees[x]!![i] >= curHeight) {
                    blockedWest = true
                    break
                }
            }
        }

        // check east (y++)
        if (!blockedEast) {
            for (i in (y+1) until trees.size) {
                if (trees[x]!![i] >= curHeight) {
                    blockedEast = true
                    break
                }
            }
        }

        // check south (x++)
        if (!blockedSouth) {
            for (i in (x+1) until trees.size) {
                if (trees[i]!![y] >= curHeight) {
                    blockedSouth = true
                    break
                }
            }
        }
        // can exit early if any ONE direction fails

        return !blockedNorth || !blockedWest || !blockedEast || !blockedSouth
    }
}