package sj.aoc2023.chassis

val TEXT_2_INT = mapOf(
    "one" to "o1ne",
    "two" to "t2wo",
    "three" to "t3hree",
    "four" to "f4our",
    "five" to "f5ive",
    "six" to "s6ix",
    "seven" to "s7even",
    "eight" to "e8ight",
    "nine" to "n9ine"
)

typealias CalibrationDocument = List<String>

fun CalibrationDocument.calibrate() = run {
    this.sumOf { cv ->
        cv.processDigits()
    }
}

fun CalibrationDocument.recalibrate() = run {
    this.sumOf { cv ->
        cv.mapWordToInt().processDigits()
    }
}

/**
 * Converts one..nine string word to digit and apply processDigits.
 * Caveats
 *  eighthree => should be 83 not 8hree as progressive change.
 */
fun String.mapWordToInt(): String = this.fold("") { acc, a ->
    val newAcc = acc + a
    val matchedText = TEXT_2_INT.asSequence().filter { (key, _) -> newAcc.contains(key) }.firstOrNull()

    if (matchedText != null) {
        newAcc.replace(matchedText.key, matchedText.value, false)
    } else {
        newAcc
    }
}


/**
 *  Converts the first and last digits of an alphanumeric string into an integer,
 *  using the first digit twice if there's no last digit, or returns 0 if no digits are present.
 */
fun String.processDigits(): Int = this.filter { it.isDigit() }.takeIf { it.isNotEmpty() }?.let { digits ->
    "${digits.first()}${digits.lastOrNull() ?: digits.first()}".toInt()
} ?: 0