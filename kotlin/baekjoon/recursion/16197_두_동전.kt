package recursion

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) {
        readln().toCharArray()
    }

    val dx = intArrayOf(0, 0, 1, -1)
    val dy = intArrayOf(1, -1, 0, 0)
    lateinit var coin1: Point; lateinit var coin2: Point
    var findFirst = false
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == 'o')
                if (!findFirst) {
                    coin1 = Point(i, j)
                    findFirst = true
                }
                else coin2 = Point(i, j)
            }
    }

    fun dfs(count: Int, coin1: Point, coin2: Point, visited: Array<BooleanArray>): Int {
        var ans = Integer.MAX_VALUE
        if (count == 10) {
            return -1
        }

        for (i in 0 until 4) {
            val dir = Point(dx[i], dy[i])
            val nextCoin1 = coin1 + dir
            val nextCoin2 = coin2 + dir
            if (nextCoin1.isThrow(n, m) && nextCoin2.isThrow(n, m)) continue

            if (nextCoin1.isThrow(n, m) && !nextCoin2.isThrow(n, m)) return count + 1
            if (!nextCoin1.isThrow(n, m) && nextCoin2.isThrow(n, m)) return count + 1

            if (board[nextCoin1.x][nextCoin1.y] != '#' && board[nextCoin2.x][nextCoin2.y] != '#') {
                visited[nextCoin1.x][nextCoin1.y] = true
                ans = maxOf(ans, dfs(count + 1, nextCoin1, nextCoin2, visited))
                visited[nextCoin1.x][nextCoin1.y] = false
            }

            if (board[nextCoin1.x][nextCoin1.y] != '#') {
                visited[nextCoin1.x][nextCoin1.y] = true
                ans = minOf(ans, dfs(count + 1, nextCoin1, coin2, visited))
                visited[nextCoin1.x][nextCoin1.y] = false
            }
            if (board[nextCoin2.x][nextCoin2.y] != '#') {
                visited[nextCoin1.x][nextCoin1.y] = true
                ans = minOf(ans, dfs(count + 1, coin1, nextCoin2, visited))
                visited[nextCoin1.x][nextCoin1.y] = false
            }
        }
        return ans
    }

    println(dfs(0, coin1, coin2, Array(n) { BooleanArray(m) { false } }))
}

private data class Point(var x: Int, var y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minusAssign(other: Point) {
        this.x -= other.x
        this.y -= other.y
    }

    fun isThrow(n: Int, m: Int): Boolean {
        return x < 0 || x >= n || y < 0 || y >= m
    }
}