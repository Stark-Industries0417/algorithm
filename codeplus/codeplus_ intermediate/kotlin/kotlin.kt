fun main() {
    val (A, B) = readlnOrNull()!!.split(" ").map { it.toList().map { it.toString().toInt() } }
    val numB = B.joinToString("").toInt()

    val l = A.size
    val check = List(l) { false }.toMutableList()
    var ans = -1

    fun go(idx: Int, num: String) {
        if (idx == l) {
            val toInt = num.toInt()
            if (toInt in (ans + 1) until numB) {
                ans = toInt
            }
            return
        }

        for (i in A.indices) {
            if (i == 0 && A[i] == 0) continue
            if (check[i]) continue

            check[i] = true
            go(idx + 1, num + A[i])
            check[i] = false
        }
    }

    go(0, "")
    println(ans)
}