interface MergeSort<T : Comparable<T>> {
    fun sort(sourceList: MutableList<T>, numberOfThreads: Int): MutableList<T>
}
