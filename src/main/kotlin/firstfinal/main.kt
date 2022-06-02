package firstfinal

import firstfinal.modul.Searcher
import java.io.IOException

fun main() {
    /*println("Enter number of search dept")
    print("Please enter a natural number greater than 1: ")

    val searchDept = readLine()?.toInt() ?: throw IOException("input mast be number? pleas repeat the input")

    println("Enter number of processors")
    print("Please enter a natural number greater than 1: ")

    val processorCount = readLine()?.toInt() ?: throw IOException("input mast be number? pleas repeat the input")

    println("Enter number of processors")
    print("Please enter a natural number greater than 1: ")

    val inputString = readLine().toString()*/
    val inputString = ""

    if (inputString.isEmpty()) {
        Searcher("https://en.wikipedia.org/wiki/Special:Random").search()
    } else {
        Searcher(inputString).search()//TODO()
    }
}
