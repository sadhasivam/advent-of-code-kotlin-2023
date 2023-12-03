const val TEST_PART1_CALIBRATION_VALUE = 142
const val PART1_CALIBRATION_VALUE = 54667
const val TEST_PART2_CALIBRATION_VALUE = 281
const val PART2_CALIBRATION_VALUE = -1

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
        cv.filter { it.isDigit() }.takeIf { it.isNotEmpty() }?.let { digits ->
            "${digits.first()}${digits.lastOrNull() ?: digits.first()}".toInt()
        } ?: 0
    }
}

fun CalibrationDocument.recalibrate() = run {
    var s = 0L
    this.sumOf { cv ->
        val newCv = cv.foldIndexed("") { _, acc, a ->
            val newAcc = acc + a
            val matchedText = TEXT_2_INT.asSequence()
                .filter { (key, _) -> newAcc.contains(key) }
                .firstOrNull()

            if (matchedText != null) {
                newAcc.replace(matchedText.key, "${matchedText.value}", false)
            } else {
                newAcc
            }
        }

        val i = newCv.filter { it.isDigit() }.takeIf { it.isNotEmpty() }?.let { digits ->
            "${digits.first()}${digits.lastOrNull() ?: digits.first()}".toInt()
        } ?: 0

        s += i
        //println("$cv,$newCv,i --> $i, s==>  $s")
        i
    }

}


data class Puzzle(
    val fileName: String,
    val isTestFile: Boolean,
    val exceptedValue: Int,
    val runner: (input: CalibrationDocument) -> Int
)

fun main() {
    fun part1(input: CalibrationDocument): Int {
        return input.calibrate()
    }

    fun part2(input: CalibrationDocument): Int {
        return input.recalibrate()
    }


    listOf(
        Puzzle("Day01/part1_test", true, TEST_PART1_CALIBRATION_VALUE, ::part1),
        Puzzle("Day01/part1", false, PART1_CALIBRATION_VALUE, ::part1),
        Puzzle("Day01/part2_test", true, TEST_PART2_CALIBRATION_VALUE, ::part2),
        Puzzle("Day01/part2", false, -1, ::part2)
    ).forEach {
        val calibrationDocument = readInput(it.fileName)
        val calibratedValue = it.runner(calibrationDocument)
        println("\n Puzzle ${it.fileName} Calibrated Value: $calibratedValue \n")
        if (it.isTestFile) {
            check(calibratedValue == it.exceptedValue)
        }

    }

}




