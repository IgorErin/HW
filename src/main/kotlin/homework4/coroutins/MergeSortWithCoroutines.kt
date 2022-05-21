import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class MergeSortWithCoroutines<T : Comparable<T>> : MergeSort<T>() {
    override fun sort(sourceList: MutableList<T>, powerOfTwoNumber: Int): MutableList<T> = runBlocking {
        return@runBlocking addCoroutine(sourceList, powerOfTwoNumber)
    }

    private suspend fun addCoroutine(list: MutableList<T>, number: Int): MutableList<T> = coroutineScope {
        val firstSubList = list.subList(0, list.size / 2)
        val secondSubList = list.subList(list.size / 2, list.size)

        return@coroutineScope when {
            list.size > 1 && number > 0 -> {
                val firstResult = async {
                    addCoroutine(firstSubList, number - 1)
                }
                val secondResult = async {
                    addCoroutine(secondSubList, number - 1)
                }

                merge(firstResult.await(), secondResult.await())
            }
            list.size > 1 -> {
                val firstList = singleThreadSort(firstSubList)
                val secondList = singleThreadSort(secondSubList)

                merge(firstList, secondList)
            }
            else -> list
        }
    }
}
