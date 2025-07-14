package baekjoon

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
        readln().fold(0) { mask, c ->
            mask or (1 shl (c - 'a'))
        }
    }

    val essential = "antic".fold(0) { mask, c ->
        mask or (1 shl (c - 'a'))
    }

    var maxCount = 0
    fun backtrack(mask: Int, start: Int, count: Int) {
        if (count == k) {
            val readable = words.count { word ->
                (word and mask) == word
            }
            maxCount = maxOf(maxCount, readable)
            return
        }

        for (i in start until 26) {
            if ((mask and (1 shl i)) != 0) continue

            backtrack(mask or (1 shl i), i + 1, count + 1)
        }
    }

    backtrack(essential, 0, 5)
    println(maxCount)
}

