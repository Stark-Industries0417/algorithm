package permutation

fun main() {
    val n = readln().toInt()
    val op = readln().split(" ")
    val candidateAns = mutableListOf<String>()
    for (i in (1 shl (n + 1)) - 1 until (1 shl 10)) {
        val ans = mutableListOf<Char>()
        for (j in 0 until 10) {
            if (i and (1 shl j) == 0) continue
            ans.add(j.digitToChar())
        }
        if (ans.size != (n + 1)) continue
        ans.permutations().forEach { perm ->
            if (operate(op, perm)) {
                candidateAns.add(perm.joinToString(""))
            }
        }
    }
    println(candidateAns.minBy { it })
    println(candidateAns.maxBy { it })
}

fun operate(op: List<String>, perm: List<Char>): Boolean {
    perm.forEachIndexed { index, c ->
        if (index == 0) return@forEachIndexed
        if (op[index - 1] == "<") {
            if (perm[index - 1].digitToInt() > c.digitToInt()) return false
        } else {
            if (perm[index - 1].digitToInt() < c.digitToInt()) return false
        }
    }
    return true
}

fun <T> List<T>.permutations(): List<List<T>> {
    if (size <= 1) return listOf(this)
    val result = mutableListOf<List<T>>()

    for (i in indices) {
        val rest = this.toMutableList().apply { removeAt(i) }
        for (perm in rest.permutations()) {
            result.add(listOf(this[i]) + perm)
        }
    }
    return result
}