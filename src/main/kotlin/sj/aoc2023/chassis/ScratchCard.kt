package sj.aoc2023.chassis

import kotlin.math.min


typealias ScratchCardList = List<String>

val cardId = Regex("^Card\\s+(?<CardId>\\d+):")

data class ScratchCard(val i: Int, val number: List<Int>, val winningNumber: List<Int>)

fun ScratchCard(i: Int, cardData: String): ScratchCard {
    val cd = cardData.replace(cardId, "").trim().split("|")
    return ScratchCard(i, cd.first().cardPoints(), cd.last().cardPoints())
}

fun ScratchCardList.winningTotal(): Int = this.mapIndexed { i, cardData ->
    ScratchCard(i, cardData)
}.sumOf {
    val points = (it.number intersect it.winningNumber.toSet())
    val total = when (points.isEmpty()) {
        true -> 0
        false -> points.foldIndexed(1) { index, acc, _ ->
            if (index == 0) acc else acc * 2
        }
    }
    total
}


fun ScratchCardList.totalCards(): Int {
    val a = this.mapIndexed { i, cardData ->
        ScratchCard(i, cardData)
    }
    return process(a).sumOf { it }
}

private fun process(scratchCard: List<ScratchCard>): IntArray {
    val scratchCardList = scratchCard.associate { it.i to (it.number intersect it.winningNumber.toSet()).count() }
    var scoreMap = IntArray(scratchCardList.size) { 1 }
    for (i in scoreMap.indices) {
        val s = scratchCardList[i] ?: 0
        val currentCount = scoreMap[i]
        (i + 1..min(i + s, scratchCardList.size - 1)).map { zscore ->
            scoreMap[zscore] += currentCount
        }
    }
    return scoreMap
}


private fun String.cardPoints(): List<Int> = this.trim().split("\\s+".toRegex()).map { it.toInt() }

