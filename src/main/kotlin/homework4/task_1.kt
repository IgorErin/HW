import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggplot
import kotlin.math.pow
import kotlin.random.Random

const val HIGH_BOUND_OF_THREADS = 9
const val HIGH_BOUND_OF_SIZE_POW = 7
const val DEFAULT_SIZE_OF_LIST = 1000
const val DELTA_SIZE_OF_LIST = 250000
const val HIGH_BOUND_OF_RANDOM_NUMBERS = 100000
const val SIZE_OF_LABELS_LIST = 70
const val MEMBERS_COUNT = 10

fun Int.pow(number: Int): Int {
    return this.toDouble().pow(number).toInt()
}

fun main() {
    val sortClass = MergeSortWithThreads<Int>()
    var size = DEFAULT_SIZE_OF_LIST
    val timeCount = mutableListOf<Int>()
    val sizeCount = mutableListOf<Int>()

    repeat(HIGH_BOUND_OF_SIZE_POW) {
        val list = MutableList(size) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }

        for (i in 0..HIGH_BOUND_OF_THREADS) {
            val startTime = System.currentTimeMillis()
            sortClass.sort(list, i)
            val totalTime = System.currentTimeMillis() - startTime

            timeCount.add(totalTime.toInt())
            sizeCount.add(size)
        }

        size += DELTA_SIZE_OF_LIST
    }

    val data = mapOf<String, Any>(
        "time" to timeCount,
        "size" to sizeCount,
        "threadsCount" to List(SIZE_OF_LABELS_LIST) {
                index -> 2.pow(index % MEMBERS_COUNT).toString()
        }
    )

    val plots = mapOf(
        "NumberOfThreads" to ggplot(data) + geomLine { x = "size"; y = "time"; color = "threadsCount" } + geomPoint()
    )

    Drawer().draw(plots)
}
