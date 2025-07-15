package bitmask

import kotlin.math.max

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    if (k < 5) {
        println(0)
        return
    }

    if (k >= 26) {
        println(n)
        return
    }

    val words = IntArray(n) {
        readln().fold(0) { mask, char ->
            mask or (1 shl (char - 'a'))
        }
    }

    var answer = 0
    fun go(mask: Int, idx: Int, count: Int) {
        if (count == k) {
            val num = words.count { word ->
                word and mask == word
            }
            answer = max(answer, num)
            return
        }

        if (idx >= 26 || (26 - idx) < k - count) return

        if (mask shl (1 shl idx) != 0) {
            go(mask, idx + 1, count)
            return
        }

        go(mask or (1 shl idx), idx + 1, count + 1)
        go(mask, idx + 1, count)
    }
    val defaultMask = "antic".fold(0) { mask, char ->
        mask or (1 shl (char - 'a'))
    }
    go(defaultMask, 0, 5)
    println(answer)
}