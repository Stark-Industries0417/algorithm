package pointer

import kotlin.math.sqrt

fun main() {
    val n = readln().toInt()
    val primes = sieveOfEratosthenes(n)

    var sum = 0; var count = 0
    var left = 0
    for (prime in primes) {
        sum += prime
        while (sum >= n) {
            if (sum == n) count++
            sum -= primes[left++]
        }
    }
    println(count)
}


private fun sieveOfEratosthenes(limit: Int): List<Int> {
    val isPrime = BooleanArray(limit + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    val sqrtN = sqrt(limit.toDouble()).toInt()
    for (i in 2..sqrtN) {
        if (!isPrime[i]) continue
        for (j in i * i..limit step i) {
            isPrime[j] = false
        }
    }
    return (2..limit).filter { isPrime[it] }
}