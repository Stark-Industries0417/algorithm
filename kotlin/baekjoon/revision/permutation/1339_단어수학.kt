package revision.permutation

import kotlin.math.pow

fun main() {
	val n = readln().toInt()
	val words = List(n) { readln() }
	var ans = 0

	words
		.flatMap { word ->
			word.mapIndexed { idx, ch ->
				ch to 10.0.pow(word.length - idx - 1)
			}
		}
		.groupBy { it.first }
		.mapValues { ( _, values ) -> values.sumOf { it.second} }
		.values
		.sortedByDescending { it }
		.forEachIndexed { idx, weight ->
			ans += ((9 - idx) * weight).toInt()
		}
	print(ans)
}
