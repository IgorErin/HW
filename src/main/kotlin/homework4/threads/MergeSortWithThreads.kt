
class MergeSortWithThreads<T : Comparable<T>> : MergeSort<T>() {
    override fun sort(sourceList: MutableList<T>, powerOfTwoNumber: Int): MutableList<T> {
        return addThread(sourceList, powerOfTwoNumber)
    }

    private fun addThread(list: MutableList<T>, number: Int): MutableList<T> {
        val firstSubList = list.subList(0, list.size / 2)
        val secondSubList = list.subList(list.size / 2, list.size)

        return when {
            list.size > 1 && number > 0 -> {
                val firstThread = SortThread(firstSubList, number)
                firstThread.start()

                val secondList = addThread(secondSubList, number - 1)
                firstThread.join()

                merge(firstThread.list, secondList)
            }
            list.size > 1 -> {
                val firstList = singleThreadSort(firstSubList)
                val secondList = singleThreadSort(secondSubList)

                merge(firstList, secondList)
            }
            else -> list
        }
    }

    private inner class SortThread(var list: MutableList<T>, val number: Int) : Thread() {
        override fun run() {
            list = addThread(list, number - 1)
        }
    }
}
