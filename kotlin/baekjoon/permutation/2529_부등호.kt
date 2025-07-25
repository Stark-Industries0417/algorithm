package permutation


fun main() {
    val n = readln().toInt()
    val op = readln().split(" ")
    var minAns = "9".repeat(10);
    var maxAns = "0".repeat(10)
    for (i in (1 shl (n + 1)) - 1 until (1 shl 10)) {
        val ans = mutableListOf<Char>()
        for (j in 0 until 10) {
            if (i and (1 shl j) == 0) continue
            ans.add(j.digitToChar())
        }
        if (ans.size != (n + 1)) continue
        generatePermutations(ans, mutableListOf(), BooleanArray(ans.size), op) { result ->
            if (minAns > result) minAns = result
            if (maxAns < result) maxAns = result
        }
    }
    println(maxAns)
    println(minAns)
}
fun isValidSequence(op: List<String>, perm: List<Char>): Boolean {
    for (i in 1 until perm.size) {
        val prev = perm[i - 1].digitToInt()
        val curr = perm[i].digitToInt()
        val operator = op[i - 1]
        if (operator == "<" && prev > curr) return false
        if (operator == ">" && prev < curr) return false
    }
    return true
}
fun generatePermutations(
    numbers: List<Char>,
    current: MutableList<Char>,
    used: BooleanArray,
    op: List<String>,
    onResult: (String) -> Unit
) {
    if (current.size >= 2 && !isValidSequence(op, current)) return

    if (current.size == numbers.size) {
        val result = current.joinToString("")
        onResult(result)
        return
    }

    for (i in numbers.indices) {
        if (used[i]) continue
        used[i] = true
        current.add(numbers[i])

        generatePermutations(numbers, current, used, op, onResult)

        current.removeAt(current.size - 1)
        used[i] = false
    }
}
