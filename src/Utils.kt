import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input file.
 */
fun readInput(pckg: String, name: String) = File("src/$pckg/resources", name)
    .readLines()

/**
 * Reads lines from the given input file.
 */
fun readInputAsInts(pckg: String, name: String) = File("src/$pckg/resources", name)
    .readLines()
    .map{ it.toInt() }


/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
