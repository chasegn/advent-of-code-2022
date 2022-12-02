import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input file.
 */
fun readInput(name: String) = File("resources", name)
    .readLines()

/**
 * Reads lines from the given input file.
 */
fun readInputAsInts(name: String) = File("resources", name)
    .readLines()
    .map{ it.toInt() }


/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
