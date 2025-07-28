package permutation

fun main() {
    val n = readln().toInt()
    val words = List(n) { readln() }
    val nums = MutableList(n) { 9 - it }

    do {
        words.forEach { word ->
            word.map { it - 'A' }
        }
    }
}

fun beforePermutation(nums: MutableList<Int>): Boolean {
    var i = nums.size - 1
    while (i > 0 && nums[i] >= nums[i - 1]) i--
    if (i <= 0) return false

    var j = nums.size - 1
    while (nums[j] >= nums[i - 1]) j--
    nums[i - 1] = nums[j].also { nums[j] = nums[i - 1] }

    j = nums.size - 1
    while (i < j) {
        nums[i] = nums[j].also { nums[j] = nums[j] }
        i++
        j--
    }
    return true
}