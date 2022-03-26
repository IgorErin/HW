package homework3

class Node<K,V>(val key: K, var value: V) {
    private var height = 1

    var left: Node<K, V>? = null
    var right: Node<K, V>? = null

    fun balance() = (right?.height ?: 0)  - (left?.height ?: 0)

    fun setHeight() {
        height = maxOf(this.right?.height ?: 0, this.right?.height ?: 0)
    }
}
