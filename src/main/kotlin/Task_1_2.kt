import kotlin.math.ceil
import kotlin.math.sqrt

fun createListOfPrime(number: Int): List<Int> {
    if (number < 2) {
        println("The number is less than required")
        return emptyList<Int>()
    }

    var listForResult: List<Int> = (2..number).toList()

    for (div in 2..ceil(sqrt((number).toDouble())).toInt()) {
        val (match, rest) = listForResult.partition { it % div != 0 || it == div }
        listForResult = match
    }

    return listForResult
}

fun printPrimeNumbers(listForResult: List<Int>) {
    for (item in listForResult) {
        print("$item ")
    }

    println()
}

fun main() {
    println("This program will write out all the primes up to the one you entered")
    print("Please enter a natural number greater than 1: ")

    val number: Int = readLine()!!.toInt()

    printPrimeNumbers(createListOfPrime(number))

    println("The prime numbers are shown above")
}
