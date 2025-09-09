package recursion

fun main() {
    val n = readln().toInt()
    val energies = readln().split(" ").map { it.toInt() }
    val temp = energies.toMutableList()

    fun getMaxEnergy(currentEnergy: Int, temp: MutableList<Int>): Int {
        var ans = Integer.MIN_VALUE
        if (temp.size == 2) return currentEnergy

        for (i in 1 until temp.size - 1) {
            val energy = temp[i - 1] * temp[i + 1]
            val elem = temp.removeAt(i)
            ans = maxOf(ans, getMaxEnergy(currentEnergy + energy, temp))
            temp.add(i, elem)
        }

        return ans
    }

    getMaxEnergy(0, temp)
        .let(::println)
}