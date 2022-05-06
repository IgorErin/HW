class MergeSortWithThreads<T : Comparable<T>> : MergeSort<T> {
    override fun sort(sourceList: MutableList<T>, numberOfThreads: Int): MutableList<T> {
        return addThread(sourceList, numberOfThreads)
    }

    private fun singleThreadSort(list: MutableList<T>): MutableList<T> {
        if (list.size > 1) {
            val firstList = singleThreadSort(list.subList(0, list.size / 2))
            val secondList = singleThreadSort(list.subList(list.size / 2, list.size))
            return merge(firstList, secondList)
        }

        return list
    }

    private fun merge(firstList: MutableList<T>, secondList: MutableList<T>): MutableList<T> {
        var firstPointer = 0
        var secondPointer = 0
        val outputList = mutableListOf<T>()

        while (firstPointer in firstList.indices || secondPointer in secondList.indices) {
            when {
                firstPointer !in firstList.indices -> {
                    outputList.add(secondList[secondPointer])
                    secondPointer++
                }
                secondPointer !in secondList.indices -> {
                    outputList.add(firstList[firstPointer])
                    firstPointer++
                }
                else -> {
                    if (firstList[firstPointer] < secondList[secondPointer]) {
                        outputList.add(firstList[firstPointer])
                        firstPointer++
                    } else {
                        outputList.add(secondList[secondPointer])
                        secondPointer++
                    }
                }
            }
        }

        return outputList
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
