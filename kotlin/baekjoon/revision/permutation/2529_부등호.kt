package revision.permutation

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val n = br.readLine().toInt()
	val operation = br.readLine().split(" ")

	val descNums = MutableList(n + 1) { 9 - it }
	val ascNums = MutableList(n + 1) { it }

	 while (true) {
		 if (operation.checkOp(descNums)) {
			 println(descNums.joinToString(""))
			 break
		 }
		 if (!descNums.prevPermutation()) break
	 }

	while (true) {
		if (operation.checkOp(ascNums)) {
			println(ascNums.joinToString(""))
			break
		}
		if (!ascNums.nextPermutation()) break
	}
}

private fun List<String>.checkOp(nums: MutableList<Int>): Boolean {
	for (i in 0 until size) {
		if (this[i] == "<" && nums[i] > nums[i + 1]) return false
		if (this[i] == ">" && nums[i] < nums[i + 1]) return false
	}
	return true
}

private fun MutableList<Int>.nextPermutation(): Boolean {
	var i = size - 1
	while (i > 0 && this[i - 1] >= this[i]) i--
	if (i <= 0) return false

	var j = size - 1
	while (this[j] <= this[i - 1]) j--
	this[i - 1] = this[j].also { this[j] = this[i - 1] }

	j = size - 1
	while (i < j) {
		this[i] = this[j].also { this[j] = this[i] }
		i++
		j--
	}

	return true
}

private fun MutableList<Int>.prevPermutation(): Boolean {
	var i = size - 1
	while (i > 0 && this[i - 1] <= this[i]) i--
	if (i <= 0) return false

	var j = size - 1
	while (this[j] >= this[i - 1]) j--
	this[i - 1] = this[j].also { this[j] = this[i - 1] }

	j = size - 1
	while (i < j) {
		this[i] = this[j].also { this[j] = this[i] }
		i++
		j--
	}

	return true
}

