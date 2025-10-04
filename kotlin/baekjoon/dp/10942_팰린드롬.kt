package dp

fun main() {
	val n = readln().toInt()
	val nums = readln().split(" ").map { it.toInt() }.toIntArray()
	val m = readln().toInt()


	val dp = Array(n) { BooleanArray(n) { false } }
	for (i in 0 until n) {
		dp[i][i] = true
	}

	for (i in 2 .. n) {
		for (j in i - 1 until n) {
			if (i == 2 && nums[j - 1] == nums[j])
				dp[j - 1][j] = true

			if (dp[j - i + 2][j - 1] && nums[j - i + 1] == nums[j])
				dp[j - i + 1][j] = true
		}
	}

	val sb = StringBuilder()
	repeat(m) {
		val (a, b) = readln().split(" ").map { it.toInt() }
		sb.append(if (dp[a - 1][b - 1]) 1 else 0).append('\n')
	}
	println(sb)
}
