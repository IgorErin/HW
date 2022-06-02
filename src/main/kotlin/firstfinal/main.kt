package firstfinal

import firstfinal.modul.HitlerSearcher

const val RAND_WIKI_URL = "https://en.wikipedia.org/wiki/Special:Random"
fun notFound(number: Int): String = "with depth $number not a single hitler was found"

fun main() {
    try {
        print("Enter number of search dept:")

        val searchDept = readLine()?.toInt()
        require(searchDept != null && searchDept > 0) {
            "search dept input error, depth must be greater than 0, but was $searchDept"
        }

        print("Enter number of processors:")

        val processorCount = readLine()?.toInt()
        require(processorCount != null && processorCount > 0) {
            "processor number input error, must be greater than 0, but was $processorCount"
        }

        print("Enter site url:")

        val inputString = readLine().toString()

        if (inputString.isEmpty()) {
            print(HitlerSearcher(RAND_WIKI_URL).search(searchDept, processorCount) ?: notFound(searchDept))
        } else {
            print(HitlerSearcher(inputString).search(searchDept, processorCount) ?: notFound(searchDept))
        }
    } catch (e: java.lang.NumberFormatException) {
        println("input error, are you sure you entered a number? restart the program pleas")
    } catch (e: java.lang.IllegalArgumentException) {
        print("$e, restart the program pleas")
    }
}
