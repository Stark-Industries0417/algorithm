package pointer

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }
    val mid = n / 2
    var count = 0

    val left = arr.slice(0 until mid)
    val right = arr.slice(mid until n)
    val rightSums = right.getAllSubsetSum().sorted()

    for (leftSum in left.getAllSubsetSum()) {
        val target = m - leftSum
        if (rightSums.binarySearch(target)) {
            count++
        }
    }

    println(count)
}

private fun List<Int>.binarySearch(target: Int): Boolean {
    var left = 0
    var right = size

    while (left < right) {
        val mid = (left + right) / 2
        if (this[mid] >= target) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return if (target == this[left]) true else false
}

private fun List<Int>.getAllSubsetSum(): Set<Int> {
    val subSetSum = mutableSetOf<Int>()
    for (i in 1 until (1 shl size)) {
        var sum = 0
        for (j in 0 until size) {
            if (i and (1 shl j) != 0) sum += this[j]
        }
        subSetSum.add(sum)
    }
    return subSetSum
}