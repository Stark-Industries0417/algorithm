package permutation

import java.lang.Math.pow
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
    var temp = 0
    val nums = mutableMapOf<Char, Int>()
    weights.entries.sortedByDescending { it.value }
        .forEachIndexed { index, entry ->
            nums[entry.key] = 9 - index
        }

    words.forEach { word ->
        var num = ""
        word.forEach { c ->
           num += nums[c]
        }
        temp += num.toInt()
    }

    println(temp)
}