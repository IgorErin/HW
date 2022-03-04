import kotlin.math.ceil
import kotlin.math.sqrt

fun createListOfPrime(number: Int?): List<Int> {

    require(number != null) { "Count must be >= 2, was null" }

    require(number >= 2) { "Count must be >= 2, was $number" }

    var primes: List<Int> = (2..number).toList()

    for (div in 2..ceil(sqrt(number.toDouble())).toInt()) {
        primes = primes.filter { it % div != 0 || it == div }
    }

    return primes
}

fun printPrimeNumbers(primes: List<Int>) {
    print(primes.joinToString(", ", "", "\n"))
    println("The prime numbers are shown above")
}

fun main() {
    println("This program will write out all the primes up to the one you entered")
    print("Please enter a natural number greater than 1: ")

    val number = readLine()?.toIntOrNull()

    try {
        printPrimeNumbers(createListOfPrime(number))
    } catch (e: IllegalArgumentException) {
        print(e.message)
    }
}
