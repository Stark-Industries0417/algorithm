package pointer

fun main() {
    val t = readln().toInt()
    val n = readln().toInt()
    val a = readln().split(" ").map { it.toInt() }
    val m = readln().toInt()
    val b = readln().split(" ").map { it.toInt() }

    val subSumA = a.getAllSubArraySums()
    val subSumB = b.getAllSubArraySums()

    var ans = 0L
    subSumA.entries.forEach { (sum, count) ->
        val target = t - sum
        if (subSumB.containsKey(target)) ans += (count * subSumB[target]!!.toLong())
    }

    println(ans)
}

private fun List<Int>.getAllSubArraySums(): Map<Int, Int> {
    val map = mutableMapOf<Int, Int>()

    for (i in indices) {
        var sum = 0
        for (j in i until size) {
            sum += this[j]
            map[sum] = map.getOrDefault(sum, 0) + 1
        }
    }

    return map
}