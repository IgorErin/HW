import kotlin.math.ceil
import kotlin.math.sqrt
import kotlin.system.exitProcess

fun createListOfPrime(number: Int): List<Int> {

    require(number >= 2) { "Count must be >= 2, was $number" }

    val primes: MutableList<Int> = (2..number).toMutableList()

    for (div in 2..ceil(sqrt(number.toDouble())).toInt()) {
        primes.retainAll { it % div != 0 || it == div }
    }

    return primes
}

fun main() {
    println("This program will write out all the primes up to the one you entered")
    print("Please enter a natural number greater than 1: ")

    val number = readLine()?.toIntOrNull()

    if (number == null) {
        print("Count must be >= 2, was null")
        exitProcess(0)
    }

    try {
        println(createListOfPrime(number).joinToString(", "))
        println("The prime numbers are shown above")
    } catch (e: IllegalArgumentException) {
        print(e.message)
    }
}
