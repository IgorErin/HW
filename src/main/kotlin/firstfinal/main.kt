package firstfinal

import firstfinal.modul.HitlerSearcher

fun main() {
    /*println("Enter number of search dept:")

    val searchDept = readLine()?.toInt()
    require(searchDept != null && searchDept > 0) {
        "search dept input error, restart the program pleas"
    }

    print("Enter number of processors:")

    val processorCount = readLine()?.toInt()
    require(processorCount != null && processorCount > 0) {
        "processor count input error, restart the program pleas"
    }

    println("Enter site url:")

    //val inputString = readLine().toString() TODO()*/
    val inputString = ""

    if (inputString.isEmpty()) {
        print(HitlerSearcher("https://en.wikipedia.org/wiki/AVL_tree").search(256, 4))
    } else {
        print(HitlerSearcher(inputString).search(256, 5))//TODO()
    }
}
