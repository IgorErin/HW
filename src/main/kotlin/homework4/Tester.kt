import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggplot
import kotlin.random.Random

class Tester(private val sortClass: MergeSort<Int>, private val testName: String) {
    fun test() {
        var size = DEFAULT_SIZE_OF_LIST
        val timeCount = mutableListOf<Int>()
        val sizeCount = mutableListOf<Int>()

        println("Please wait calculations in progress")

        repeat(HIGH_BOUND_OF_ITERATIONS) {
            val list = MutableList(size) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }

            for (i in 0..HIGH_BOUND_OF_THREADS) {
                val startTime = System.currentTimeMillis()
                sortClass.sort(list, i)
                val totalTime = System.currentTimeMillis() - startTime

                timeCount.add(totalTime.toInt())
                sizeCount.add(size)
            }

            println("Complete for size = $size")

            size += DELTA_SIZE_OF_LIST
        }

        val data = mapOf<String, Any>(
            "nanoTime" to timeCount,
            "size" to sizeCount,
            "$testName count" to List(SIZE_OF_LABELS_LIST) {
                    index -> 2.pow(index % PLOT_MEMBERS_COUNT).toString()
            }
        )

        val plots = mapOf(
            "$testName test" to ggplot(data) + geomLine {
                x = "size"; y = "nanoTime"; color = "$testName count"
            } + geomPoint()
        )

        Drawer().draw(plots)
    }

    companion object {
        const val HIGH_BOUND_OF_THREADS = 9
        const val HIGH_BOUND_OF_ITERATIONS = 7
        const val DEFAULT_SIZE_OF_LIST = 1000
        const val DELTA_SIZE_OF_LIST = 250000
        const val HIGH_BOUND_OF_RANDOM_NUMBERS = 100000
        const val SIZE_OF_LABELS_LIST = 70
        const val PLOT_MEMBERS_COUNT = 10
    }
}
