
/**
 * Day 07 for Advent of Code 2022
 * https://adventofcode.com/2022/day/7
 */
class Day07 : Day {
    override val inputFileName: String = "Day07"
    override val test1Expected: Int = 95437
    override val test2Expected: Int = 24933642

    /**
     * Accepted solution: 1642503
     */
    override fun part1(input: List<String>): Int {
        val root = parseFSTree(input)
        return getAllDirs(root)
            .filter { it.size!! <= 100000 }
            .fold(0) { acc, dir -> acc + dir.size!! }
    }

    /**
     * Accepted solution:
     */
    override fun part2(input: List<String>): Int {
        val root = parseFSTree(input)
        val allDirs = getAllDirs(root).sortedBy { it.size }

        val deficit = 30000000 - (70000000 - root.size!!)

        return allDirs.first { it.size!! >= deficit }.size!!
    }

    private fun parseFSTree(input: List<String>) : ElfDirectory {
        val root = ElfDirectory("/", null)
        var currentDir = root

        input.forEach { line ->
            val tokens = line.split(' ')

            if (tokens[0].startsWith('d')) {
                currentDir.contents.add(ElfDirectory(tokens[1], currentDir))
            } else if (!tokens[0].startsWith('$')) {
                currentDir.contents.add(ElfFile(line))
            } else if (tokens[1] == "cd") {
                currentDir = when (tokens[2]) {
                    "/" -> root
                    ".." -> currentDir.parent!!
                    else -> currentDir.contents.filter{ it.name == tokens[2] }[0] as ElfDirectory
                }
            }
        }

        return root
    }

    private fun getAllDirs(node: ElfDirectory): List<ElfDirectory> {
        val dirs = mutableListOf(node)

        node.contents
            .filterIsInstance<ElfDirectory>()
            .forEach { dirs.addAll(getAllDirs(it)) }

        return dirs
    }

    interface ElfData {
        val name: String
    }

    data class ElfFile(override val name: String, val size: Int) : ElfData {
        constructor(input: String) : this(input.split(' ')[1], input.split(' ')[0].toInt())
    }

    data class ElfDirectory(override val name: String, val parent: ElfDirectory?) : ElfData {
        val contents = mutableListOf<ElfData>()
        val size: Int? = null
            get() = field ?: size()

        private fun size(): Int {
            var size = contents.filterIsInstance<ElfFile>()
                .map { it.size }
                .fold(0) { acc, s -> acc + s }
            size = contents.filterIsInstance<ElfDirectory>()
                .filter { it.contents.isNotEmpty() }
                .map { it.size() }
                .fold(size) { acc, s -> acc + s }

            return size
        }
    }

}