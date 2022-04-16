class Tree<K : Comparable<K>, V> : MutableMap<K, V> {
    override var size: Int = 0
        private set

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = mutableSetOf<MutableMap.MutableEntry<K, V>>().apply {
            addAll(map(root) { node: Node<K, V> -> Entry(node.key, node.value) })
        }

    override val keys: MutableSet<K>
        get() = mutableSetOf<K>().apply { addAll(map(root) { node: Node<K, V> -> node.key }) }

    override val values: MutableList<V>
        get() = mutableListOf<V>().apply { addAll(map(root) { node: Node<K, V> -> node.value }) }

    private var root: Node<K, V>? = null

    override fun containsKey(key: K): Boolean = searchWithKey(root, key) !== null

    override fun containsValue(value: V): Boolean = value in values

    override fun get(key: K): V? = searchWithKey(root, key)?.value

    override fun isEmpty(): Boolean = root == null

    override fun clear() {
        size = 0
        root = null
    }

    override fun put(key: K, value: V): V? {
        val valueForReturn = searchWithKey(root, key)?.value

        root = insert(root, value, key)
        size += 1

        return valueForReturn
    }

    override fun putAll(from: Map<out K, V>) {
        from.entries.forEach { entry ->
            root = insert(root, entry.value, entry.key)
            size += 1
        }
    }

    override fun remove(key: K): V? {
        val value = searchWithKey(root, key)?.value

        if (value != null) {
            size -= 1
            root = removeWithKey(root, key)
        }

        return value
    }

    override fun toString(): String {
        return root.toString()
    }

    companion object {
        fun <K : Comparable<K>, V, T> map(node: Node<K, V>?, lambda: (Node<K, V>) -> T): Collection<T> {
            node ?: return mutableListOf()

            val list = mutableListOf(lambda(node))
            list.addAll(map(node.right, lambda))
            list.addAll(map(node.left, lambda))

            return list
        }

        private fun <K : Comparable<K>, V> insert(node: Node<K, V>?, value: V, key: K): Node<K, V> {
            node ?: return Node(key, value)

            when {
                key == node.key -> {
                    node.value = value
                }
                key > node.key -> {
                    node.right = insert(node.right, value, key)
                }
                key < node.key -> {
                    node.left = insert(node.left, value, key)
                }
            }

            return node.balance()
        }

        private fun <K : Comparable<K>, V> searchWithKey(node: Node<K, V>?, key: K): Node<K, V>? {
            node ?: return null

            var searchNode = node

            when {
                key < node.key -> {
                    searchNode = searchWithKey(node.left, key)
                }
                key > node.key -> {
                    searchNode = searchWithKey(node.right, key)
                }
            }

            return searchNode
        }

        private fun <K : Comparable<K>, V> removeWithKey(node: Node<K, V>?, key: K): Node<K, V>? {
            return when {
                node == null -> null
                key < node.key -> {
                    node.left = removeWithKey(node.left, key)
                    node.balance()
                }
                key > node.key -> {
                    node.right = removeWithKey(node.right, key)
                    node.balance()
                }
                key == node.key -> {
                    val minNode = node.right?.findMin()
                    minNode ?: return node.left

                    minNode.right = node.right?.removeMin()
                    minNode.left = node.left

                    minNode.balance()
                }
                else -> node.balance()
            }
        }
    }
}
