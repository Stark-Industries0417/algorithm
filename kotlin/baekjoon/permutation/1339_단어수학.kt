package permutation

import kotlin.math.pow


fun main() {
    val n = readln().toInt()
    val words = List(n) { readln() }
    val weights = mutableMapOf<Char, Int>()
    words.forEach { word ->
        word.forEachIndexed { index, c ->
            val charWeights = 10.0.pow((word.length - index - 1).toDouble())
            weights[c] = weights.getOrDefault(c, 0) + charWeights.toInt()
        }
    }
    var total = 0; var pow = 9
    weights.values.sortedByDescending { it }
        .forEach {
             total += pow * it
            pow -= 1
        }
    println(total)
}