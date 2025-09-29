package dp

fun main() {
	val (n, m) = readln().split(" ").map { it.toInt() }
	val miro = Array(n) {
		readln().split(" ").map { it.toInt() }.toIntArray()
	}
	val dp = Array(n) { IntArray(m) }

	for (i in 0 until n) {
		for (j in 0 until m) {
			dp[i][j] = miro[i][j] + when {
				i == 0 && j == 0 -> 0
				i == 0 -> dp[i][j - 1]
				j == 0 -> dp[i - 1][j]
				else -> maxOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
			}
		}
	}

	println(dp[n - 1][m - 1])
}