package permutation

fun main() {
    val k = readln().toInt()
    val operate = readln().split(" ")

    val small = MutableList(k + 1) { it }
    val big = MutableList(k + 1) { 9 - it }

    while (true) {
        if (check(small, operate)) break
        if (!nextPermutation(small)) break
    }
    while (true) {
        if (check(big, operate)) break
        if (!prevPermutation(big)) break
    }

    println(big.joinToString(""))
    println(small.joinToString(""))

}

private fun check(nums: List<Int>, op: List<String>): Boolean {
    for (i in 0 until op.size) {
        if (op[i] == "<" && nums[i] > nums[i + 1]) return false
        if (op[i] == ">" && nums[i] < nums[i + 1]) return false
    }
    return true
}


private fun nextPermutation(nums: MutableList<Int>): Boolean {
    var i = nums.size - 1
    while (i > 0 && nums[i - 1] >= nums[i]) i--
    if (i <= 0) return false

    var j = nums.size - 1
    while (nums[j] <= nums[i - 1]) j--
    nums[i - 1] = nums[j].also { nums[j] = nums[i - 1] }

    j = nums.size - 1
    while (i < j) {
        nums[i] = nums[j].also { nums[j] = nums[i] }
        i++
        j--
    }
    return true
}

private fun prevPermutation(nums: MutableList<Int>): Boolean {
    var i = nums.size - 1
    while (i > 0 && nums[i - 1] <= nums[i]) i--
    if (i <= 0) return false

    var j = nums.size - 1
    while (nums[j] >= nums[i - 1]) j--
    nums[i - 1] = nums[j].also { nums[j] = nums[i - 1] }

    j = nums.size - 1
    while (i < j) {
        nums[i] = nums[j].also { nums[j] = nums[i] }
        i++
        j--
    }
    return true
}

