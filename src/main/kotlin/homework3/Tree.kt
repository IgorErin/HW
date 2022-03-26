package homework3

import kotlin.math.min

class Tree<K, V>(val Compare: (K, K) -> Int,
                 override var size: Int,
                 override val entries: MutableSet<MutableMap.MutableEntry<K, V>>,
                 override val keys: MutableSet<K>,
                 override val values: MutableCollection<V>
                 ):  MutableMap<K, V> {

    var tree: Node<K, V>? = null

    private fun rightRotate(node: Node<K, V>): Node<K, V> {
        val nodeForRotate = node.left

        node.left = nodeForRotate?.right
        nodeForRotate?.right = node

        node.setHeight()
        nodeForRotate?.setHeight()

        return nodeForRotate!!
    }

    private fun leftRotate(node: Node<K, V>): Node<K, V> {
        val nodeForRotate = node.right

        node.right = nodeForRotate?.left
        nodeForRotate?.left = node

        node.setHeight()
        nodeForRotate?.setHeight()

        return nodeForRotate!!
    }

    private fun balance(node: Node<K, V>): Node<K, V> {
        node.setHeight()

        when(node.balance()) {
            2 -> {
                if ((node.right?.balance() ?: 0) < 0) {
                    node.right = rightRotate(node.right!!)
                }
                return leftRotate(node)
            }
            -2 -> {
                if ((node.left?.balance() ?: 0) > 0) {
                    node.left = leftRotate(node.left!!)
                }
                return rightRotate(node)
            }
        }
        return node
    }

    private fun insert(node: Node<K, V>?, value: V, key: K): Node<K, V> {
        size += 1
        values.add(value)
        keys.add(key)
        entries.add(MutableMap.)

        if (node == null) {
            return Node<K, V>(key, value)
        }
        if (Compare(node.key, key) == 0) {
            node.value = value
            return balance(node)
        }
        if (Compare(node.key, key) > 0) {
            if (node.right == null) {
                node.right = Node<K, V>(key, value)
                return balance(node)
            }
            node.right = insert(node.right!!, value, key)
        }
        if (Compare(node.key, key) < 0) {
            if (node.left == null) {
                node.left = Node<K, V>(key, value)
                return balance(node)
            }
            node.left = insert(node.left!!, value, key)
        }
        return balance(node)
    }

    private fun searchWithKey(node: Node<K, V>?, key: K): Node<K, V>? {
        if (node == null) {
            return null
        }
        if (Compare(node.key, key) == 0) {
            return node
        }
        if (Compare(node.key, key) > 0) {
            return searchWithKey(node.left, key)
        }
        return searchWithKey(node.right, key)
    }

    private fun searchWithValue(node: Node<K, V>?, value: V): Boolean{
        if (node == null) {
            return false
        }
        if (node.value == value) {
            return true
        }
        return searchWithValue(node.left, value) && searchWithValue(node.right, value)
    }

    private fun findMin(node: Node<K, V>?): Node<K, V>? {
        if (node == null) {
            return null
        }
        if (node.left == null) {
            return node
        }
        return findMin(node.left!!)
    }

    private fun removeWithKey(node: Node<K, V>?, key: K): Node<K, V>? {
        if(node == null) {
            return null
        }
        if (Compare(key, node.key) > 0) {
            node.right = removeWithKey(node.right, key)
            return balance(node)
        }
        else if (Compare(key, node.key) < 0) {
            node.left = removeWithKey(node, key)
            return balance(node)
        } else {
            if (node.right == null) {
                return node.left
            }
            val minNode = findMin(node.right)!!
            minNode.right = removeMin(node.right!!)
            minNode.left = node.left
            return balance(minNode)
        }
    }

    private fun removeMin(node: Node<K, V>): Node<K, V>? {
        if (node.left == null){
            return node.right
        }
        node.left = removeMin(node.left!!)
        return balance(node)
    }

    override fun containsKey(key: K): Boolean {
        return when(searchWithKey(tree, key)) {
            null -> false
            else -> true
        }
    }

    override fun containsValue(value: V): Boolean {
        return searchWithValue(tree, value)
    }

    override fun get(key: K): V? {
        return searchWithKey(tree, key)?.value
    }

    override fun isEmpty(): Boolean {
        return when(tree) {
            null -> true
            else -> false
        }
    }

    override fun clear() {
        tree = null
    }

    override fun put(key: K, value: V): V? {
        if (tree == null ) {
            tree = Node(key, value)
            return null
        }

        val valueForReturn = searchWithKey(tree, key)?.value
        insert(tree!!, value, key)

        return valueForReturn
    }

    override fun putAll(from: Map<out K, V>) {
        for (item in from.entries) {
            tree = insert(tree, item.value, item.key)
        }
    }

    override fun remove(key: K): V? {
        val node = searchWithKey(tree, key)?.value
        removeWithKey(tree, key)
        return node
    }
}