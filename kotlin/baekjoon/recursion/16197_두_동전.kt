package recursion

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) {
        readln().toCharArray()
    }

    val dx = intArrayOf(0, 0, 1, -1)
    val dy = intArrayOf(1, -1, 0, 0)

    lateinit var coin1: Point
    lateinit var coin2: Point
    var findFirst = false

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == 'o') {
                if (!findFirst) {
                    coin1 = Point(i, j)
                    findFirst = true
                } else {
                    coin2 = Point(i, j)
                }
            }
        }
    }

    fun dfs(count: Int, coin1: Point, coin2: Point): Int {
        if (count >= 10) {
            return -1
        }

        var ans = Integer.MAX_VALUE

        for (i in 0 until 4) {
            val dir = Point(dx[i], dy[i])
            var nextCoin1 = coin1 + dir
            var nextCoin2 = coin2 + dir

            if (nextCoin1.isOut(n, m) && nextCoin2.isOut(n, m))
                continue
            if (nextCoin1.isOut(n, m) || nextCoin2.isOut(n, m))
                return count + 1

            if (board[nextCoin1.x][nextCoin1.y] == '#') {
                nextCoin1 = coin1
            }
            if (board[nextCoin2.x][nextCoin2.y] == '#') {
                nextCoin2 = coin2
            }

            if (nextCoin1 != nextCoin2 && (nextCoin1 != coin1 || nextCoin2 != coin2)) {
                val result = dfs(count + 1, nextCoin1, nextCoin2)
                if (result != -1) {
                    ans = minOf(ans, result)
                }
            }
        }
        return if (ans == Integer.MAX_VALUE) -1 else ans
    }

    val result = dfs(0, coin1, coin2)
    println(result)
}

private data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    fun isOut(n: Int, m: Int): Boolean {
        return x < 0 || x >= n || y < 0 || y >= m
    }
}