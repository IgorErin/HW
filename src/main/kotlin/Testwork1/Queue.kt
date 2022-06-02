import java.util.TreeSet

class Queue<E, K : Comparable<K>>(comparator: (Pair<E, K>, Pair<E, K>) -> Int) {
    private var size = 0

    private val tree = TreeSet<Pair<E, K>>(comparator)

    fun enqueue(element: E, priority: K) {
        tree.add(Pair(element, priority))
        size += 1
    }

    fun peek(): E {
        require(size > 0) { "empty queue" }

        return tree.last().first
    }

    fun remove() {
        require(size > 0) { "empty queue" }

        size--

        tree.remove(tree.first())
    }

    fun roll(): E {
        require(size > 0) { "empty queue" }

        val max = tree.first().first
        tree.remove(tree.first())
        return max
    }
}
