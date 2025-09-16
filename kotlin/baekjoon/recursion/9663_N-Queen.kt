package recursion

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val cols = IntArray(n) { -1 }
    var count = 0

    fun isSafe(row: Int, col: Int): Boolean {
        for (i in 0 until row) {
            if (cols[i] == col) return false
            if (abs(i - row) == abs(cols[i] - col)) return false
        }
        return true
    }

    fun go(row: Int) {
        if (row == n) {
            count++
            return
        }

        for (col in 0 until n) {
            if (isSafe(row, col)) {
                cols[row] = col
                go(row + 1)
                cols[row] = -1
            }
        }
    }

    go(0)
    println(count)
}