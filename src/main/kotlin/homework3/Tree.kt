
@Suppress("TooManyFunctions", "ReturnCount")
class Tree<K, V>(val compare: (K, K) -> Int) : MutableMap<K, V> {
    override var size: Int = 0
        private set

    override val entries = mutableSetOf<MutableMap.MutableEntry<K, V>>()

    override val keys = mutableSetOf<K>()

    override val values = mutableListOf<V>()

    private var root: Node<K, V>? = null

    private fun leftRightRotate(node: Node<K, V>): Node<K, V> {
        val leftNode = node.left ?: node

        node.left = leftRotate(leftNode)

        return rightRotate(node)
    }

    private fun rightLeftRotate(node: Node<K, V>): Node<K, V> {
        val rightNode = node.right ?: node

        node.right = rightRotate(rightNode)

        return leftRotate(node)
    }

    private fun rightRotate(node: Node<K, V>): Node<K, V> {
        val nodeForRotate = node.left ?: return node

        node.left = nodeForRotate.right
        nodeForRotate.right = node

        node.setHeight()
        nodeForRotate.setHeight()

        return nodeForRotate
    }

    private fun leftRotate(node: Node<K, V>): Node<K, V> {
        val nodeForRotate = node.right ?: return node

        node.right = nodeForRotate.left
        nodeForRotate.left = node

        node.setHeight()
        nodeForRotate.setHeight()

        return nodeForRotate
    }

    private fun balance(node: Node<K, V>?): Node<K, V>? {
        node ?: return null

        node.setHeight()
        var nodeForReturn = node

        when (node.balance()) {
            HEIGHT_BOUND -> {
                if ((node.right?.balance() ?: 0) < 0) {
                    return rightLeftRotate(node)
                }
                nodeForReturn = leftRotate(node)
            }
            LOWER_BOUND -> {
                if ((node.left?.balance() ?: 0) > 0) {
                    return leftRightRotate(node)
                }
                nodeForReturn = rightRotate(node)
            }
        }

        return nodeForReturn
    }

    private fun insert(node: Node<K, V>?, value: V, key: K): Node<K, V>? {
        node ?: return Node(key, value)

        when {
            compare(key, node.key) == 0 -> {
                node.value = value
            }
            compare(key, node.key) > 0 -> {
                node.right = insert(node.right, value, key)
            }
            compare(key, node.key) < 0 -> {
                node.left = insert(node.left, value, key)
            }
        }

        return balance(node)
    }

    private fun searchWithKey(node: Node<K, V>?, key: K): Node<K, V>? {
        node ?: return null

        when {
            compare(key, node.key) < 0 -> {
                return searchWithKey(node.left, key)
            }
            compare(key, node.key) > 0 -> {
                return searchWithKey(node.right, key)
            }
        }

        return node
    }

    private fun searchWithValue(node: Node<K, V>?, value: V): Boolean {
        node ?: return false

        if (node.value == value) {
            return true
        }

        return searchWithValue(node.left, value) || searchWithValue(node.right, value)
    }

    private fun removeWithKey(node: Node<K, V>?, key: K): Node<K, V>? {
        node ?: return null

        when {
            compare(key, node.key) < 0 -> {
                node.left = removeWithKey(node.left, key)
            }
            compare(key, node.key) > 0 -> {
                node.right = removeWithKey(node.right, key)
            }
            else -> {
                val minNode = findMin(node.right)

                minNode ?: return balance(node.left)

                minNode.right = removeMin(node.right)
                minNode.left = node.left

                return balance(minNode)
            }
        }

        return balance(node)
    }

    private fun findMin(node: Node<K, V>?): Node<K, V>? {
        node ?: return null

        node.left ?: return node

        return findMin(node.left)
    }

    private fun removeMin(node: Node<K, V>?): Node<K, V>? {
        node ?: return null

        node.left ?: return node.right

        node.left = removeMin(node.left)

        return balance(node)
    }

    override fun containsKey(key: K): Boolean = searchWithKey(root, key) != null

    override fun containsValue(value: V): Boolean {
        return searchWithValue(root, value)
    }

    override fun get(key: K): V? {
        return searchWithKey(root, key)?.value
    }

    override fun isEmpty(): Boolean = root == null

    override fun clear() {
        size = 0
        values.clear()
        keys.clear()
        entries.clear()
        root = null
    }

    override fun put(key: K, value: V): V? {
        val valueForReturn = searchWithKey(root, key)?.value
        root = insert(root, value, key)

        size += 1
        values.add(value)
        keys.add(key)
        entries.add(Entry(value, key))

        return valueForReturn
    }

    override fun putAll(from: Map<out K, V>) {
        for (item in from.entries) {
            root = insert(root, item.value, item.key)
            size += 1
            values.add(item.value)
            keys.add(item.key)
            entries.add(Entry(item.value, item.key))
        }
    }

    override fun remove(key: K): V? {
        val value = searchWithKey(root, key)?.value

        if (value != null) {
            entries.remove(Entry(value, key))
            size -= 1
            values.remove(value)
            keys.remove(key)
            root = removeWithKey(root, key)
        }

        return value
    }

    companion object {
        const val LOWER_BOUND = -2
        const val HEIGHT_BOUND = 2
    }
}
