package baekjoon.bitmask

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }
    val check = BooleanArray(2_000_001) { false }

    for (i in 1 until (1 shl n)) {
        var s = 0
        for (j in 0 until n) {
            if (i and (1 shl j) != 0) s += arr[j]
        }
        check[s] = true
    }

    for (i in 1 until check.size) {
        if (!check[i]) {
            println(i)
            return
        }
    }
}