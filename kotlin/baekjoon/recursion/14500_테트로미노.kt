package recursion

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val dx = intArrayOf(0, 0, 1, -1)
    val dy = intArrayOf(1, -1, 0, 0)
    var ans = Integer.MAX_VALUE

    fun findScore(x: Int, y: Int, choice: Int, currentSum: Int) {
        if (choice == 4) {
            ans = maxOf(ans, currentSum)
            return
        }
        if (x < 0 || x >= n || y < 0 || y >= m) return

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            findScore(nx, ny, choice + 1, currentSum + graph[x][y])
        }
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            findScore(i, j, 0, 0)

            //TODO ㅗ, ㅜ, ㅓ, ㅏ 구현
        }
    }
}