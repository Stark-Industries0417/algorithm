package permutation

import kotlin.math.max

fun main() {
    val n = readln().toInt()
    val words = List(n) { readln() }
    val totalLength = words.fold(0) { acc, word -> acc + word.length }
    val nums = MutableList(totalLength) { 9 - it }
    val alphaNum = mutableMapOf<Char, Char>()
    val alpabet = words.flatMap { it.asIterable() }.toSet()
    var ans = -1
    while (true) {
        alpabet.forEachIndexed { idx, char ->
            alphaNum[char] = nums[idx].digitToChar()
        }
        var temp = 0
        for (word in words) {
            var num = ""
            for (c in word) {
                num += alphaNum[c]
            }
            temp += num.toInt()
        }
        ans = max(temp, ans)
        if (!beforePermutation(nums)) {
            println(ans)
            return
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
        nums[i] = nums[j].also { nums[j] = nums[i] }
        i++
        j--
    }
    return true
}