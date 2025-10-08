package dp

fun main() {
	val n = readln().toInt()
	val query = mutableListOf<Int>()

	repeat(n) {
		query.add(readln().toInt())
	}

	val dp = IntArray(11) { 0 }
	dp[0] = 1
	dp[1] = 1

	for (i in 2 until 11) {
		for (j in 1..3) {
			if (j > i) break
			dp[i] += dp[i - j]
		}
	}

	query.map { dp[it] }.forEach(::println)
}