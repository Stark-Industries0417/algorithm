
package permutation

import kotlin.math.pow


fun main() {
    val n = readln().toInt()
    val words = List(n) { readln() }
    var ans = 0
    words
        .flatMap { word ->
            word.mapIndexed { index, char ->
                char to 10.0.pow(word.length - index - 1)
            }
        }
        .groupBy { it.first }
        .mapValues { (_, values) -> values.sumOf { it.second} }
        .values
        .sortedByDescending { it }
        .forEachIndexed { index, weight ->
            ans += ((9 - index) * weight).toInt()
        }
    println(ans)
}