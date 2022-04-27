package homework4

import java.lang.Math.pow
import kotlin.math.pow
import kotlin.random.Random

fun main() {
    val randomValues = MutableList(1000000) { Random.nextInt(0, 100) }

    for (i in 1..10) {
        SortWithThreads(randomValues).sort(1)
    }

    val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    for (i in list) {

        val start = System.nanoTime()
        SortWithThreads(randomValues).sort(i)

        println("${System.nanoTime() - start} : ${2.toDouble().pow(i).toInt()}  threads")
    }
}