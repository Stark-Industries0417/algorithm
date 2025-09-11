package recursion

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val visited = BooleanArray(n * n) { false }
    var count = 0

    fun go(idx: Int, queen: Int) {
        if (idx == (n*n - 1)) {
            if (queen == 0) count++
            return
        }

        if (!check(n, idx, visited)) {
            go(idx + 1, queen)
            return
        }

        visited[idx] = true
        go(idx + 1, queen - 1)
        visited[idx] = false
        go (idx + 1, queen)
    }

    go(0, n)
    println(count)
}

private fun check(n: Int, idx: Int, visited: BooleanArray): Boolean {
    visited.forEachIndexed { i, visited ->
        if (visited) {
            val x = i / n
            val y = i % n

            val myX = idx / n
            val myY = idx % n
            if (x == myX || y == myY) return false
            if (x + y == myX + myY) return false
            if (abs(x - y) == abs(myX - myY)) return false
        }
    }
    return true
}