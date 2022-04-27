
interface MergeSort<T : Comparable<T>> {
    fun sort(numberOfThreads: Int): MutableList<T>
}
