package permutation

fun main() {
    val n = readln().toInt()
    val op = readln().split(" ")

    for (i in 1 until (1 shl 9)) {
        var idx = 0
        var ans = ""
        for (j in 0 until 10) {
            if (i and (1 shl j) != 0) {
                val result = operate(idx, op[idx], ans)
                if (!result) break
                idx++
                ans += j.toChar()
            }
        }
    }
}

fun operate(idx: Int, op: String, ans: String): Boolean {
    if (idx == 0) return true
    if (op == "<")
        if (ans[idx - 1].digitToInt() > ans[idx].digitToInt()) return false
    if (op == ">")
        if (ans[idx - 1].digitToInt() < ans[idx].digitToInt()) return false
    return true
}
