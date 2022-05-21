abstract class MergeSort<T : Comparable<T>> {
    abstract fun sort(sourceList: MutableList<T>, powerOfTwoNumber: Int): MutableList<T>

    protected fun singleThreadSort(list: MutableList<T>): MutableList<T> {
        if (list.size > 1) {
            val firstList = singleThreadSort(list.subList(0, list.size / 2))
            val secondList = singleThreadSort(list.subList(list.size / 2, list.size))
            return merge(firstList, secondList)
        }

        return list
    }

    protected fun merge(firstList: MutableList<T>, secondList: MutableList<T>): MutableList<T> {
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
}
