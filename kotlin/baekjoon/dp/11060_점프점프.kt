package dp

fun main() {
    val n = readln().toInt()
    val miro = readln().split(" ").map { it.toInt() }.toIntArray()
    val dp = IntArray(n) { Integer.MAX_VALUE}
    dp[0] = 0

    for (i in 0 until n) {
        for (j in 0 until i) {
            if (j + miro[j] < i) continue
            if (dp[j] == Integer.MAX_VALUE) continue

            if (dp[i] > dp[j] + 1)
                dp[i] = dp[j] + 1
        }
    }
    println(if (dp[n - 1] == Integer.MAX_VALUE) -1 else dp[n - 1])
}