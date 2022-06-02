package firstfinal

import firstfinal.modul.HitlerSearcher

const val RAND_WIKI_URL = "https://en.wikipedia.org/wiki/Special:Random"
fun notFound(number: Int): String = "with depth $number not a single hitler was found"

fun main() {
    try {
        print("Enter number of search dept:")

        val searchDept = readLine()?.toInt()
        require(searchDept != null && searchDept > 0) {
            "search dept input error, restart the program pleas"
        }

        print("Enter number of processors:")

        val processorCount = readLine()?.toInt()
        require(processorCount != null && processorCount > 0) {
            "processor count input error, restart the program pleas"
        }

        print("Enter site url:")

        val inputString = readLine().toString()

        if (inputString.isEmpty()) {
            print(HitlerSearcher(RAND_WIKI_URL).search(searchDept, processorCount) ?: notFound(searchDept))
        } else {
            print(HitlerSearcher(inputString).search(searchDept, processorCount) ?: notFound(searchDept))
        }
    } catch (e: java.lang.IllegalArgumentException) {
        println(e)
    }
}
