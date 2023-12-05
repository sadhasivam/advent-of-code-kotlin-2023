package sj.aoc2023.chassis

typealias  GearScheme = List<String>

fun GearScheme.getPartNumbers() : List<Int> {
    val machineValues = mutableListOf<Int>()

    for ((i, line) in this.withIndex()) {
        val digit = StringBuilder()
        for ((j, char) in line.withIndex()) {
            if (char.isDigit()) {
                digit.append(char)
                if(j == line.length -1 && isPartNumber(this, i, j - digit.length, j)) {
                    machineValues.add(digit.toString().toInt())
                    digit.clear()
                }
            } else if (digit.isNotEmpty()) {
                val startIdx = kotlin.math.max(0, j - digit.length - 1)
                val endIdx = kotlin.math.min(line.length, j + 1)

                if (isPartNumber(this, i, startIdx, endIdx)) {
                    machineValues.add(digit.toString().toInt())
                }
                digit.clear()
            }
        }
    }
    return machineValues
}

private fun isPartNumber(input: List<String>, i: Int, startIdx: Int, endIdx: Int): Boolean {
    val currentLineCheck = input[i].hasPartNumber(startIdx, endIdx)
    val prevLineCheck = i > 0 && input[i - 1].hasPartNumber(startIdx, endIdx)
    val nextLineCheck =  i + 1 < input.size && input[i + 1].hasPartNumber(startIdx, endIdx)
    return currentLineCheck || prevLineCheck || nextLineCheck
}

fun String.hasPartNumber(startIdx: Int, endIdx: Int) : Boolean =
    this.substring(startIdx, endIdx).any { it !in '0'..'9' && it != '.' }
