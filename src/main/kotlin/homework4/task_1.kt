import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggplot
import kotlin.random.Random

const val HIGHER_BOUND_OF_POW = 9
const val HIGHER_BOUND_OF_SIZE_POW = 7
const val DEFAULT_SIZE = 1000
const val DELTA_SIZE = 250000
const val HIGH_BOUND_OF_RANDOM_NUMBERS = 100000
const val SIZE_OF_LABELS_LIST = 70
const val MEMBERS_COUNT = 10

fun main() {
    val a = MergeSortWithThreads<Int>()
    var size = DEFAULT_SIZE
    val timeCount = mutableListOf<Int>()
    val sizeCount = mutableListOf<Int>()

    repeat(HIGHER_BOUND_OF_SIZE_POW) {
        val list = MutableList(size) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }

        for (i in 0..HIGHER_BOUND_OF_POW) {

            val startTime = System.currentTimeMillis()
            println(a.sort(list, i))
            val totalTime = System.currentTimeMillis() - startTime

            timeCount.add(totalTime.toInt())
            sizeCount.add(size)
        }

        size += DELTA_SIZE
    }

    val data = mapOf<String, Any>(
        "time" to timeCount,
        "size" to sizeCount,
        "threadsCount" to List(SIZE_OF_LABELS_LIST) {
                index -> listOf("1", "2", "4", "8", "16", "32", "64", "128", "256", "512")[index % MEMBERS_COUNT]
        }
    )

    val plots = mapOf(
        "NumberOfThreads" to ggplot(data) + geomLine { x = "size"; y = "time"; color = "threadsCount" } + geomPoint(),
    )

    Drawer().draw(plots)
}
