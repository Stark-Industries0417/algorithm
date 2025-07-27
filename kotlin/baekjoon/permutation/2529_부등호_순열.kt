package permutation

fun main() {

}


fun nextPermutation(nums: MutableList<Int>): Boolean {
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